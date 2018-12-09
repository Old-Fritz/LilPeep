package SimpleUser;

import CrudServices.DocumentKindCrudService;
import CrudServices.UserDocumentCrudService;
import CrudServices.UserDocumentFieldCrudService;
import Entities.*;
import Rabbit.RabbitSender;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    private DocumentKind document;
    private User user;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = ssoManager.getCurrentUser(req);
        // getting document
        try {
            long documentID = Long.parseLong(req.getParameter("documentID"));
            document = documentKindCrudService.findById(documentID);
            if(document==null)
                throw new Exception();
        }catch (Exception e) {
            sender.sendErr("Ошибка при открытии документа: " + e.toString());
            return;
        }

        // check for existing of this document
        if(userDocumentCrudService.findByUserAndDocument(user,document)!=null) {
            sender.sendErr("Такой документ уже существует");
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // send document in jsp to show
        req.setAttribute("document",document);
        req.getRequestDispatcher("/SimpleUser/JSP/AddedUserDocument.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDocument userDocument = new UserDocument(document, user);
        userDocumentCrudService.save(userDocument);

        // change all field values
        for(int i = 0;i<document.getFieldsCount();i++) {
            String fieldValue = req.getParameter("field" + i);
            if(fieldValue == null)
                fieldValue = "";
            UserDocumentField field = new UserDocumentField(fieldValue,document.getFields().get(i),userDocument);
            try {
                userDocumentFieldCrudService.save(field);
            } catch (Exception e) {
                sender.sendErr("Ошибка при сохранении документа: " + e.toString());
            }
        }
        resp.sendRedirect(req.getContextPath()+"/user/");
    }
}
