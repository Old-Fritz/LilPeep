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

    private UserForm form;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user
        User user = ssoManager.getCurrentUser(req);
        if(user==null) {
            sender.sendErr("Такого пользователя не существует");
            return;
        }

        try {
            long formID = Long.parseLong(req.getParameter("formID"));
            form = userFormCrudService.findById(formID);
            // check that form and user are same
            if(form==null||form.getUser().getId()!=user.getId())
                throw new Exception();
        }catch (Exception e){
            resp.sendRedirect(req.getContextPath()+"/owner/");
            return;
        }

        req.setAttribute("form", form);
        req.setAttribute("user", user);
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                FormDocument document = formDocumentCrudService.findById(documentInd);
                req.setAttribute("document", document);
                req.getRequestDispatcher("/Users/Owner/includes/FormDocument.jsp").forward(req,resp);
                return;
            }catch (Exception e){
                sender.sendErr("Не удалось получить документ: " + e.toString());
            }
        }

        // Go back if all gets are faileds
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
            deleteDocument(Long.parseLong(req.getParameter("documentID")));
            return;
        }

        if(type != null && type.equals("deleteForm")) {
            deleteForm();
            return;
        }

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
        req.getRequestDispatcher("/owner").forward(req,resp);
    }

    private void deleteDocument(long id) throws ServletException, IOException {// Удвление документа
        try{
            FormDocument document = formDocumentCrudService.findById(id);
            List<FormDocumentField> fields =formDocumentFieldCrudService.findByFormDocument(document);
            for(FormDocumentField field:fields)
                formDocumentFieldCrudService.deleteById(field.getId());
            formDocumentCrudService.deleteById(id);
            form.setDocumentCount(form.getDocumentCount()-1);

        }catch (Exception e){
            sender.sendErr("Ошибка при удалении документа: " + e.toString());
        }

    }

    private void deleteForm() throws ServletException, IOException {
        // Удаление формы
        try{
            List<FormDocument> documents = formDocumentCrudService.findByUserForm(form);
            for(FormDocument document : documents)
                deleteDocument(document.getId());
            userFormCrudService.deleteById(form.getId());
        }catch (Exception e) {
            sender.sendErr("Ошибка при удалении формы: " + e.toString());
        }
    }

    /**
     * Создание документа
     * @param req HTTP-запрос
     */
    private void createDocument(HttpServletRequest req, HttpServletResponse resp) {
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

            // Create and save empty document
            FormDocument document = new FormDocument(form.getFormDocuments().size(),documentKind,form);
            formDocumentCrudService.save(document);

            for(Field field:documentKind.getFields())
            {
                FormDocumentField formField = new FormDocumentField(document,field,false);
                document.getFormDocumentFields().add(formField);
                formDocumentFieldCrudService.save(formField);
            }
            form.getFormDocuments().add(document);
            form.setDocumentCount(form.getDocumentCount()+1);

    // return id for next show
            resp.getWriter().write(document.getId()+"");

}catch (Exception e){
        sender.sendErr("Ошибка при создании документа: " + e.toString());
        }
        }
}
