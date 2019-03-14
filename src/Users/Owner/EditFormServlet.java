package Users.Owner;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
import DataBaseAcces.CrudServices.FormDocumentCrudService;
import DataBaseAcces.CrudServices.FormDocumentFieldCrudService;
import DataBaseAcces.CrudServices.UserFormCrudService;
import DataBaseAcces.Entities.*;
import ExternalServices.Rabbit.CockieUtils;
import ExternalServices.Rabbit.RabbitSender;
import ExternalServices.Security.SSOManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Сервлет изменения формы
 */
@WebServlet(name = "EditFormServlet", urlPatterns = {"/owner/editForm"})
public class EditFormServlet extends HttpServlet {
    @EJB
    private SSOManager ssoManager;
    @EJB
    private UserFormCrudService userFormCrudService;
    @EJB
    private DocumentKindCrudService documentKindCrudService;
    @EJB
    private FormDocumentCrudService formDocumentCrudService;
    @EJB
    private FormDocumentFieldCrudService formDocumentFieldCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get user
        User user = ssoManager.getCurrentUser(req);
        if(user==null) {
            sender.sendErr("Такого пользователя не существует");
            return;
        }

        // get current by id
        UserForm form = getForm(req, user);
        if(form==null)
        {
            resp.sendRedirect(req.getContextPath()+"/owner/");
            return;
        }

        // check for new form
        UserForm newForm = (UserForm)req.getSession().getAttribute("newForm");
        Boolean isNewForm;
        if(newForm == form)
            isNewForm = true;
        else
            isNewForm = false;

        req.setAttribute("form", form);
        req.setAttribute("user", user);
        req.setAttribute("isNewForm", isNewForm);
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isNewForm = (Boolean)req.getAttribute("isNewForm");
        UserForm form = (UserForm)req.getAttribute("form");

        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page"))
        {
            req.setAttribute("documents", formDocumentCrudService.findByUserForm(form));
            req.getRequestDispatcher("/Users/Owner/JSP/AddForm.jsp").forward(req,resp);
            return;
        }

        // return only list search of documents
        if(type.equals("searchList"))
        {
            String text = req.getParameter("text");
            if(text==null)
                text="";
            List<DocumentKind> documents = documentKindCrudService.findNotUsedByUserFormAndName(form,text);
            // send document in jsp to show
            req.setAttribute("documents",documents);
            req.getRequestDispatcher("/Users/Owner/includes/SearchDocumentList.jsp").forward(req,resp);
            return;
        }

        // return document with fields
        if(type.equals("formDocument"))
        {
            try{
                long documentInd = Long.parseLong(req.getParameter("documentID"));
                FormDocument document;
                if(isNewForm)
                    document = form.getFormDocuments().get((int)documentInd);
                else
                    document = formDocumentCrudService.findById(documentInd);

                req.setAttribute("document", document);
                req.getRequestDispatcher("/Users/Owner/includes/FormDocument.jsp").forward(req,resp);
                return;
            }catch (Exception e){
                sender.sendErr("Не удалось получить документ: " + e.toString());
            }
        }

        // Go back if all gets are failed
        resp.sendRedirect(req.getContextPath()+"/owner");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");

        // create user document
        if(type != null && type.equals("addDocument")) {
            createDocument(req, resp);
            return;
        }

        if(type != null && type.equals("deleteDocument")) {
            deleteDocument(req, Long.parseLong(req.getParameter("documentID")));
            return;
        }

        if(type != null && type.equals("deleteForm")) {
            deleteForm(req);
            return;
        }

        // save in default
        saveForm(req,resp);

    }

    private void deleteDocument(HttpServletRequest req, long id) throws ServletException, IOException {
        Boolean isNewForm = (Boolean)req.getAttribute("isNewForm");
        UserForm form = (UserForm)req.getAttribute("form");

        FormDocument document;
        if(isNewForm)
            document = form.getFormDocuments().get((int)id);
        else
            document = formDocumentCrudService.findById(id);
        if(document == null)
        {
              sender.sendErr("Неверное id документа: ");
              return;
        }

        form.setDocumentCount(form.getDocumentCount()-1);
        form.getFormDocuments().removeIf(doc->doc.getId()==id);
        if(!isNewForm)
        {
            document.setUserForm(null);
            document = formDocumentCrudService.update(document);
            List<FormDocumentField> fields =formDocumentFieldCrudService.findByFormDocument(document);
            for(FormDocumentField field:fields)
            {
                field.setFormDocument(null);
                field = formDocumentFieldCrudService.update(field);
                formDocumentFieldCrudService.deleteById(field.getId());
            }
            formDocumentCrudService.deleteById(id);
        }

    }

    private void deleteForm(HttpServletRequest req) throws ServletException, IOException {
        Boolean isNewForm = (Boolean)req.getAttribute("isNewForm");
        UserForm form = (UserForm)req.getAttribute("form");
        // Удаление формы
        if(isNewForm)
            req.getSession().removeAttribute("newForm");
        else
            userFormCrudService.deleteById(form.getId());
    }

    private void saveForm(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException
    {
        Boolean isNewForm = (Boolean)req.getAttribute("isNewForm");
        UserForm form = (UserForm)req.getAttribute("form");

        form.setName(req.getParameter("name"));
        form.setUrl(req.getParameter("url"));
        for(FormDocument document:form.getFormDocuments())
        {
            for(FormDocumentField field:document.getFormDocumentFields())
            {
                if(req.getParameter(document.getId()+"_"+field.getId())!=null)
                    field.setChecked(true);
                else
                    field.setChecked(false);
            }
        }
        // save in DB if this is new form
        if(isNewForm)
        {

            UserForm newForm = new UserForm(form.getDocumentCount(), form.getName(),form.getUrl(), form.getOrder(), form.getUser());
            userFormCrudService.save(newForm);
            for(FormDocument document:form.getFormDocuments())
            {
                FormDocument newDocument = new FormDocument(document.getOrder(), document.getDocumentKind(),newForm);
                newForm.getFormDocuments().add(newDocument);
                formDocumentCrudService.save(newDocument);
                for(FormDocumentField field:document.getFormDocumentFields())
                {
                    FormDocumentField newField = new FormDocumentField(newDocument, field.getField(),field.isChecked());
                    newDocument.getFormDocumentFields().add(newField);
                    formDocumentFieldCrudService.save(newField);
                }
                formDocumentCrudService.update(newDocument);
            }
            userFormCrudService.update(newForm);
            req.getSession().removeAttribute("newForm");
        }
        else
            userFormCrudService.update(form);
        resp.sendRedirect(req.getContextPath()+"/owner/");
    }
    /**
     * Создание документа
     * @param req HTTP-запрос
     */
    private void createDocument(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Boolean isNewForm = (Boolean)req.getAttribute("isNewForm");
        UserForm form = (UserForm)req.getAttribute("form");

        try{
            // get document kind in some way
            long documentID = Long.parseLong(req.getParameter("documentID"));
            DocumentKind documentKind;
            if(documentID>=0)
                documentKind = documentKindCrudService.findById(documentID);
            else
                documentKind = documentKindCrudService.findByName(req.getParameter("name")).get(0);

            if(documentKind==null)
                throw new Exception();

            // check for existing in documents
            for (FormDocument formDocument : form.getFormDocuments()) {
                if(formDocument.getDocumentKind().getId()==documentKind.getId())
                {
                    sender.sendErr("Нельзя добавить два одинаковых документа");
                    throw new Exception();
                }
            }

            // Create and save empty document
            FormDocument document = new FormDocument(form.getFormDocuments().size(),documentKind,form);
            if(isNewForm)
                document.setId(form.getDocumentCount());
            else
                formDocumentCrudService.save(document);

            // create fields
            List<Field> fields = documentKind.getFields();
            for(int i = 0;i<fields.size();i++)
            {

                FormDocumentField formField = new FormDocumentField(document,fields.get(i),false);
                document.getFormDocumentFields().add(formField);
                if(isNewForm)
                    formField.setId(i);
                else
                    formDocumentFieldCrudService.save(formField);
            }
            form.getFormDocuments().add(document);
            form.setDocumentCount(form.getDocumentCount()+1);

            // return id for next show
            resp.getWriter().write(document.getId()+"");

        }catch (Exception e){
            sender.sendErr("Ошибка при создании документа: " + e.toString());
            resp.getWriter().write("-1");
        }

    }

    private UserForm getForm(HttpServletRequest req, User user)
    {
        UserForm outForm;
        String formIDStr = req.getParameter("formID");
        // if sended without form id this is new form or null
        if(formIDStr == null)
            return (UserForm)req.getSession().getAttribute("newForm");


        // get form by id
        long formID = Long.parseLong(req.getParameter("formID"));
        if(formID<0)
            return (UserForm)req.getSession().getAttribute("newForm");

        outForm = userFormCrudService.findById(formID);
        // check that form and user are same
        if(outForm==null||outForm.getUser().getId()!=user.getId())
            return null;

        return outForm;
    }
}

