package Users.SimpleUser;

import DataBaseAcces.CrudServices.UserDocumentCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserDocument;
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
 * Сервлет документа пользователя
 */
@WebServlet(name="UserDocumentList",urlPatterns = {"/user/documents", "/user/"})
public class UserDocumentsServlet extends HttpServlet {

    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserDocumentCrudService userDocumentCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");

        // security check
        User user = ssoManager.getCurrentUser(req);
        if(user==null) {
            sender.sendErr(req, "Такого пользователя не существует");
            resp.sendRedirect(req.getContextPath()+"/user/");
            return;
        }

        // show simple page without any info
        if(type == null || type.equals("page")) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/Users/SimpleUser/JSP/UserDocuments.jsp").forward(req,resp);
            return;
        }

        // return only list of documents
        String text = req.getParameter("text");
        if(text==null)
            text="";
        List<UserDocument> documents = userDocumentCrudService.findByUserAndName(user,text);
        req.setAttribute("documents", documents);
        req.setAttribute("action", "editDocument");
        req.getRequestDispatcher("/Users/SimpleUser/includes/UserDocumentsList.jsp").forward(req,resp);
    }
}
