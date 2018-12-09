package SimpleUser;

import CrudServices.DocumentKindCrudService;
import CrudServices.UserDocumentCrudService;
import Entities.DocumentKind;
import Entities.User;
import Entities.UserDocument;
import Rabbit.RabbitSender;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Сервлет добавления документа
 */
@WebServlet(name="AddUserDocument",urlPatterns = {"/user/addDocument"})
public class AddUserDocumentServlet extends HttpServlet{

    @EJB
    private SSOManager ssoManager;

    @EJB
    private DocumentKindCrudService documentKindCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page")) {
            req.getRequestDispatcher("/SimpleUser/JSP/AddUserDocument.jsp").forward(req,resp);
            resp.sendRedirect(req.getContextPath()+"/user/");
            return;
        }

        // get user
        User user = ssoManager.getCurrentUser(req);
        if(user==null) {
            sender.sendErr("Такого пользователя не существует");
            resp.sendRedirect(req.getContextPath()+"/user/");
            return;
        }

        // return only list of documents
        String text = req.getParameter("text");
        if(text==null)
            text="";
        List<DocumentKind> documents = documentKindCrudService.findNotUsedByUserAndName(user,text);
        req.setAttribute("documents", documents);
        req.getRequestDispatcher("/SimpleUser/JSP/DocumentsList.jsp").forward(req,resp);
    }
}
