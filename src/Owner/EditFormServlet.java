package Owner;

import CrudServices.DocumentKindCrudService;
import CrudServices.FormDocumentCrudService;
import CrudServices.FormDocumentFieldCrudService;
import CrudServices.UserFormCrudService;
import Entities.*;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditFormServlet", urlPatterns = {"editForm"})
public class EditFormServlet extends HttpServlet {
    @EJB
    SSOManager ssoManager;
    @EJB
    UserFormCrudService userFormCrudService;
    @EJB
    DocumentKindCrudService documentKindCrudService;
    @EJB
    FormDocumentCrudService formDocumentCrudService;
    @EJB
    FormDocumentFieldCrudService formDocumentFieldCrudService;

    private UserForm form;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user
        User user = ssoManager.getCurrentUser(req);
        if(user==null)
        {
            // RMQ
            resp.sendRedirect(req.getContextPath()+"/logout");
            return;
        }

        try {
            long formID = Long.parseLong(req.getParameter("formID"));
            form = userFormCrudService.findById(formID);
            if(form==null||form.getUser().getId()!=user.getId())
                throw new Exception();
        }catch (Exception e){
            // RMQ
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
            req.getRequestDispatcher("/Owner/JSP/AddForm.jsp").forward(req,resp);
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
            req.getRequestDispatcher("/Owner/JSP/FormDocumentList.jsp").forward(req,resp);
            return;
        }

        // return document with fields
        if(type.equals("document"))
        {
            try{
                long documentInd = Long.parseLong(req.getParameter("documentInd"));
                FormDocument document = formDocumentCrudService.findById(documentInd);
                req.setAttribute("document", document);
                req.getRequestDispatcher("/Owner/JSP/FormDocument.jsp").forward(req,resp);
                return;
            }catch (Exception e){
                //RMQ
            }
        }

        // send document in jsp to show
        resp.sendRedirect(req.getContextPath()+"owner");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null)
        {
            // RMQ
            return;
        }
        // show simple page without any info
        if(type == "document")
        {
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
        if(type == null)
        {
            // RMQ
            return;
        }
        // show simple page without any info
        if(type == "document")
        {
            try{
                long documentID = Long.parseLong(req.getParameter("documentID"));
                formDocumentFieldCrudService.deleteById(documentID);
                form.setDocumentCount(form.getDocumentCount()+1);

            }catch (Exception e){
                // RMQ
            }
            return;
        }
    }

    void createDocument(HttpServletRequest req)
    {
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
            // RMQ
        }
    }

}
