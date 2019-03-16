package Users.SimpleUser;

import DataBaseAcces.CrudServices.UserDocumentCrudService;
import DataBaseAcces.CrudServices.UserDocumentFieldCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserDocument;
import DataBaseAcces.Entities.UserDocumentField;
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
 * Сервлет редактирования документа пользователя
 */
@WebServlet(name="EditUserDocument",urlPatterns = {"/user/editDocument"})
public class EditUserDocumentServlet extends HttpServlet {
    @EJB
    private UserDocumentCrudService userDocumentCrudService;

    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserDocumentFieldCrudService userDocumentFieldCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ssoManager.getCurrentUser(req);
        UserDocument document;
        // getting document
        try {
            long documentID = Long.parseLong(req.getParameter("documentID"));
            document = userDocumentCrudService.findById(documentID);
            if(document==null)
                throw new Exception();
        }catch (Exception e) {
            sender.sendErr("Ошибка при открытии документа: " + e.toString());
            resp.sendRedirect(req.getContextPath()+"/user/");
            return;
        }

        // security check
        if(document.getUser().getId()!=user.getId()) {
            sender.sendErr("Ошибка доступа");
            resp.sendRedirect(req.getContextPath()+"/user/");
            return;
        }

        req.setAttribute("document", document);
        req.setAttribute("user", user);

        if(req.getParameter("isDelete")!=null)
        {
            delete(req,resp);
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDocument document = (UserDocument)req.getAttribute("document") ;
        // send document in jsp to show
        req.setAttribute("document",document);
        req.getRequestDispatcher("/Users/SimpleUser/JSP/EditUserDocument.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDocument document = (UserDocument)req.getAttribute("document") ;

        List<UserDocumentField> fields = document.getUserDocumentFields();
        // change all field values
        for(int i = 0;i<fields.size();i++) {
            String fieldValue = req.getParameter("field" + i);
            if(fieldValue == null)
                fieldValue = "";
            fields.get(i).setValue(fieldValue);
            fields.set(i,userDocumentFieldCrudService.update(fields.get(i)));
        }
        resp.sendRedirect(req.getContextPath()+"/user/");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserDocument document = (UserDocument)req.getAttribute("document") ;

        userDocumentCrudService.deleteById(document.getId());
        resp.sendRedirect(req.getContextPath()+"/user/");
    }
}
