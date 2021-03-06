package Authentication;

import DataBaseAcces.CrudServices.UserKindCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserKind;
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
 * Сервлет авторизации
 */

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserKindCrudService userKindCrudService;

    @EJB
    private RabbitSender sender;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ssoManager.getCurrentUser(req);
        // forward to login page if user hasn't logged in
        if(user==null)
        {
            req.getRequestDispatcher("/Authentication/Login.jsp").forward(req, resp);
            return;
        }

        // go to requirable page if user has logged in
        resp.sendRedirect(req.getContextPath() + user.getUserKind().getUrlPattern());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get parameters
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email == null || password == null) {
            sender.sendErr(req, "Отсутствует e-mail и/или пароль");
            resp.sendRedirect(req.getRequestURI());
            return;
        }
        UserKind userKind;
        try{
            long kindID = Long.parseLong(req.getParameter("kindID"));
            userKind = userKindCrudService.findById(kindID);
            if(userKind == null)
                throw new Exception();
        } catch (Exception e) {
            sender.sendErr(req, "Такого типа записи не существует");
            resp.sendRedirect(req.getRequestURI());
            return;
        }

        // try login
        if(!ssoManager.login(req, resp, email,password,userKind)) {
            sender.sendErr(req, "Авторизация не удалась, попробуйте ещё раз");
            resp.sendRedirect(req.getRequestURI());
            return;
        }

        // go to requirable page
        resp.sendRedirect(req.getRequestURI());
    }
}
