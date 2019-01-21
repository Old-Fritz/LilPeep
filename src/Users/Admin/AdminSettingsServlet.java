package Admin;

import Entities.Settings;
import Entities.User;
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
 * Сервлет настроек администратора
 */
@WebServlet(name="AdminSettings",urlPatterns = {"/admin/Settings"})
public class AdminSettingsServlet extends HttpServlet {

    @EJB
    private SSOManager ssoManager;

    @EJB
    private RabbitSender sender;

    private List<Settings> settings;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Admin/JSP/AdminSettings").forward(req, resp);
        User user = ssoManager.getCurrentUser(req);
        if (user == null) {
            sender.sendErr("Ошибка доступа");
            resp.sendRedirect(req.getContextPath() + "/logout");
            return;
        }
        settings = user.getSettings();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (int i = 0; i < settings.size(); i++) {
            String settingValue = req.getParameter("setting" + i);
            if (settingValue == null)
                settingValue = "";
            settings.get(i).setValue(settingValue);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/");
    }
}