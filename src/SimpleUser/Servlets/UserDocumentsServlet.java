package SimpleUser.Servlets;

import CrudServices.UserCrudService;
import CrudServices.UserDocumentCrudService;
import Entities.User;
import Entities.UserDocument;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="UserDocumentList",urlPatterns = {"/user/documents"})
public class UserDocumentsServlet extends HttpServlet {

    @EJB
    private UserCrudService userCrudService;
    @EJB
    private UserDocumentCrudService userDocumentCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null || type.equals("page" ))
        {
            req.getRequestDispatcher("/SimpleUser/JSP/UserDocuments.jsp").forward(req,resp);
            return;
        }

        int userID = Integer.parseInt(req.getParameter("userID"));
        User user = userCrudService.findById(userID);
        String text = req.getParameter("text");
        List<UserDocument> documents = userDocumentCrudService.findByUserAndName(user,text);
        req.setAttribute("documents", documents);
        req.getRequestDispatcher("/SimpleUser/JSP/UserDocumentList.jsp").forward(req,resp);
    }
}
