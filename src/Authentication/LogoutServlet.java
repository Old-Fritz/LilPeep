package Authentication;

import Entities.User;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LogoutServlet", urlPatterns = {"/logout"} )
public class LogoutServlet extends HttpServlet{

    @EJB
    SSOManager ssoManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ssoManager.logout(req, resp);
        resp.sendRedirect(req.getContextPath());
    }
}