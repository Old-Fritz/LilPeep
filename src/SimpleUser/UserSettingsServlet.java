package SimpleUser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="UserSettings",urlPatterns = {"/user/settings"})
public class UserSettingsServlet extends HttpServlet {
}
