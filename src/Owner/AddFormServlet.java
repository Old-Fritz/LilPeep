package Owner;

import CrudServices.UserFormCrudService;
import Entities.User;
import Entities.UserForm;
import Rabbit.RabbitSender;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddFormServlet", urlPatterns = {"addForm"})
public class AddFormServlet extends HttpServlet {
    @EJB
    SSOManager ssoManager;

    @EJB
    UserFormCrudService userFormCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user
        User user = ssoManager.getCurrentUser(req);
        if (user == null) {
            sender.sendErr("Такого пользователя не существует");
            resp.sendRedirect(req.getContextPath() + "/logout");
            return;
        }

        UserForm form = new UserForm(0, "", "", userFormCrudService.findByUser(user).size(), user);
        userFormCrudService.save(form);

        resp.sendRedirect(req.getContextPath() + "/editForm?formID=" + form.getId());
    }
}
