package Users.Owner;

import DataBaseAcces.CrudServices.UserFormCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserForm;
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
 * Сервлет формы
 */
@WebServlet(name = "FormsServlet",urlPatterns = {"/owner/","/owner/forms"})
public class FormsServlet extends HttpServlet{
    @EJB
    private SSOManager ssoManager;
    @EJB
    private UserFormCrudService userFormCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page")) {
            req.getRequestDispatcher("/Users/Owner/JSP/Forms.jsp").forward(req,resp);
            return;
        }

        // security check
        User user = ssoManager.getCurrentUser(req);
        if(user==null) {
            sender.sendErr("Ошибка доступа");
            resp.sendRedirect(req.getContextPath()+"/logout");
            return;
        }

        // return only list of documents
        String text = req.getParameter("text");
        if(text==null)
            text="";
        List<UserForm> forms = userFormCrudService.findByUserAndName(user,text);
        req.setAttribute("forms", forms);
        req.getRequestDispatcher("/Users/Owner/includes/FormsList.jsp").forward(req,resp);
    }
}
