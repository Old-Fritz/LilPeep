package SimpleUser;

import CrudServices.UserCrudService;
import CrudServices.UserDocumentCrudService;
import Entities.User;
import Entities.UserDocument;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="UserDocumentList",urlPatterns = {"/user/documents", "/user/"})
public class UserDocumentsServlet extends HttpServlet {

    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserDocumentCrudService userDocumentCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page"))
        {
            req.getRequestDispatcher("/SimpleUser/JSP/UserDocuments.jsp").forward(req,resp);
            return;
        }

        // security check
        User user = ssoManager.getCurrentUser(req);
        if(user==null)
        {
            // RMQ
            resp.sendRedirect(req.getContextPath()+"/logout");
            return;
        }

        // return only list of documents
        String text = req.getParameter("text");
        if(text==null)
            text="";
        List<UserDocument> documents = userDocumentCrudService.findByUserAndName(user,text);
        req.setAttribute("documents", documents);
        req.getRequestDispatcher("/SimpleUser/JSP/UserDocumentList.jsp").forward(req,resp);
    }
}