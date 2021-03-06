package Users.SimpleUser;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
import DataBaseAcces.CrudServices.UserDocumentCrudService;
import DataBaseAcces.CrudServices.UserDocumentFieldCrudService;
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


/**
 * Сервлет успешного добавления документа
 */
@WebServlet(name="AddedUserDocument",urlPatterns = {"/user/addedDocument"})
public class AddedUserDocumentServlet extends HttpServlet {
    @EJB
    private UserDocumentCrudService userDocumentCrudService;

    @EJB
    private SSOManager ssoManager;

    @EJB
    private DocumentKindCrudService documentKindCrudService;

    @EJB
    private UserDocumentFieldCrudService userDocumentFieldCrudService;

    @EJB
    private RabbitSender sender;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        DocumentKind document;

        user = ssoManager.getCurrentUser(req);
        // getting document
        try {
            long documentID = Long.parseLong(req.getParameter("documentID"));
            document = documentKindCrudService.findById(documentID);
            if(document==null)
                throw new Exception();
        }catch (Exception e) {
            sender.sendErr(req, "Ошибка при открытии документа: " + e.toString());
            resp.sendRedirect(req.getContextPath()+"/user");
            return;
        }

        // check for existing of this document
        if(userDocumentCrudService.findByUserAndDocument(user,document)!=null) {
            sender.sendErr(req, "Такой документ уже существует");
            resp.sendRedirect(req.getContextPath()+"/user");
            return;
        }

        req.setAttribute("document",document);
        req.setAttribute("user", user);
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // send document in jsp to show
        req.getRequestDispatcher("/Users/SimpleUser/JSP/AddedUserDocument.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DocumentKind document = (DocumentKind)req.getAttribute("document");
        User user = (User)req.getAttribute("user");

        UserDocument userDocument = new UserDocument(document, user);
        userDocumentCrudService.save(userDocument);

        // change all field values
        for(int i = 0;i<document.getFields().size();i++) {
            String fieldValue = req.getParameter("field" + i);
            if(fieldValue == null)
                fieldValue = "";
            UserDocumentField field = new UserDocumentField(fieldValue,document.getFields().get(i),userDocument);
            try {
                userDocumentFieldCrudService.save(field);
            } catch (Exception e) {
                sender.sendErr(req, "Ошибка при сохранении документа: " + e.toString());
            }
        }
        resp.sendRedirect(req.getContextPath()+"/user/");
    }
}
