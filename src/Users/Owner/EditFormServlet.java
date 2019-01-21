package Users.Owner;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
import DataBaseAcces.CrudServices.FormDocumentCrudService;
import DataBaseAcces.CrudServices.FormDocumentFieldCrudService;
import DataBaseAcces.CrudServices.UserFormCrudService;
import DataBaseAcces.Entities.*;
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
            resp.sendRedirect(req.getContextPath()+"/logout");
            return;
        }

        try {
            long formID = Long.parseLong(req.getParameter("formID"));
            form = userFormCrudService.findById(formID);
            if(form==null||form.getUser().getId()!=user.getId())
                throw new Exception();
        }catch (Exception e){
            sender.sendErr("Не удалось получить форму: " + e.toString());
            resp.sendRedirect(req.getContextPath()+"/owner/");
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page"))
        {
            req.getRequestDispatcher("/Users/Owner/JSP/AddForm.jsp").forward(req,resp);
            return;
        }

        // return only list of documents
        String text = req.getParameter("text");
        if(text==null)
            text="";
        if(type.equals("list"))
        {
            List<DocumentKind> documents = documentKindCrudService.findNotUsedByUserFormAndName(form,text);
            // send document in jsp to show
            req.setAttribute("documents",documents);
            req.getRequestDispatcher("/Users/Owner/JSP/FormDocumentList.jsp").forward(req,resp);
            return;
        }

        // return document with fields
        if(type.equals("document"))
        {
            try{
                long documentInd = Long.parseLong(req.getParameter("documentInd"));
                FormDocument document = formDocumentCrudService.findById(documentInd);
                req.setAttribute("document", document);
                req.getRequestDispatcher("/Users/Owner/JSP/FormDocument.jsp").forward(req,resp);
                return;
            }catch (Exception e){
                sender.sendErr("Не удалось получить документ: " + e.toString());
            }
        }

        // send document in jsp to show
        resp.sendRedirect(req.getContextPath()+"owner");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null) {
            sender.sendErr("Такого параметра не существует");
            return;
        }
        // show simple page without any info
        if(type.equals("document")) {
            createDocument(req);
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
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null) {
            sender.sendErr("Такого параметра не существует");
            return;
        }
        // show simple page without any info
        if(type.equals("document")) {
            try{
                long documentID = Long.parseLong(req.getParameter("documentID"));
                formDocumentFieldCrudService.deleteById(documentID);
                form.setDocumentCount(form.getDocumentCount()+1);

            }catch (Exception e){
                sender.sendErr("Ошибка при удалении документа: " + e.toString());
            }
        }
    }

    /**
     * Создание документа
     * @param req HTTP-запрос
     */
    private void createDocument(HttpServletRequest req) {
        try{
            long documentID = Long.parseLong(req.getParameter("documentID"));

            DocumentKind documentKind = documentKindCrudService.findById(documentID);
            if(documentKind==null)
                throw new Exception();

            FormDocument document = new FormDocument(form.getFormDocuments().size(),documentKind,form);
            formDocumentCrudService.save(document);

            for(Field field:documentKind.getFields())
            {
                FormDocumentField formField = new FormDocumentField(document,field,false);
                formDocumentFieldCrudService.save(formField);
            }

            form.setDocumentCount(form.getDocumentCount()+1);

        }catch (Exception e){
            sender.sendErr("Ошибка при удалении документа: " + e.toString());
        }
    }

}