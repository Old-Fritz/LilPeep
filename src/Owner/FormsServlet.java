package Owner;

import CrudServices.UserFormCrudService;
import Entities.User;
import Entities.UserForm;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FormsServlet",urlPatterns = {"ownwer/","owner/forms"})
public class FormsServlet extends HttpServlet{
    @EJB
    private SSOManager ssoManager;
    @EJB
    private UserFormCrudService userFormCrudService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page"))
        {
            req.getRequestDispatcher("/Owner/JSP/Forms.jsp").forward(req,resp);
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
        List<UserForm> forms = userFormCrudService.findByUserAndName(user,text);
        req.setAttribute("forms", forms);
        req.getRequestDispatcher("/Owner/JSP/FormsList.jsp").forward(req,resp);
    }
}
