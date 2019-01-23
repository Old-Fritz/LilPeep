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

/**
 * Сервлет добавления формы
 */
@WebServlet(name = "AddFormServlet", urlPatterns = {"/owner/addForm"})
public class AddFormServlet extends HttpServlet {
    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserFormCrudService userFormCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user
        sender.init(CockieUtils.getSessionCookie(req, resp).getValue());
        User user = ssoManager.getCurrentUser(req);
        if (user == null) {
            sender.sendErr("Такого пользователя не существует");
            resp.sendRedirect(req.getContextPath() + "/logout");
            return;
        }

        UserForm form = new UserForm(0, "", "", userFormCrudService.findByUser(user).size(), user);
        userFormCrudService.save(form);

        resp.sendRedirect(req.getContextPath() + "/owner/editForm?formID=" + form.getId());
    }
}
