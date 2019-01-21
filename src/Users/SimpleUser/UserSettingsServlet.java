package Users.SimpleUser;

import DataBaseAcces.Entities.Settings;
import DataBaseAcces.Entities.User;
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
 * Сервлет настроек пользователя
 */
@WebServlet(name="UserSettings",urlPatterns = {"/user/settings"})
public class UserSettingsServlet extends HttpServlet {

    @EJB
    private SSOManager ssoManager;

    @EJB
    private RabbitSender sender;

    private List<Settings> settings;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Users/SimpleUser/JSP/UserSettings").forward(req,resp);
        User user = ssoManager.getCurrentUser(req);
        if(user==null) {
            sender.sendErr("Ошибка доступа");
            resp.sendRedirect(req.getContextPath()+"/logout");
            return;
        }
        settings = user.getSettings();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(int i = 0;i<settings.size();i++) {
            String settingValue = req.getParameter("setting" + i);
            if(settingValue == null)
                settingValue = "";
            settings.get(i).setValue(settingValue);
        }
        resp.sendRedirect(req.getContextPath()+"/user/");
    }
}

