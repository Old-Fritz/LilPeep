package Authentication;

import CrudServices.UserCrudService;
import CrudServices.UserKindCrudService;
import Entities.User;
import Entities.UserKind;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {
    @EJB
    private SSOManager ssoManager;

    @EJB
    private UserKindCrudService userKindCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ssoManager.getCurrentUser(req);
        // forward to register page if user hasn't logged in
        if (user == null) {
            req.getRequestDispatcher("/Authentication/Registration.jsp").forward(req, resp);
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
        String passwordRepeat = req.getParameter("passwordRepeat");
        if (email == null || password == null) {
            //RMQ
            resp.sendRedirect(req.getRequestURI());
            return;
        }
        UserKind userKind;
        try {
            long kindID = Long.parseLong(req.getParameter("kindID"));
            userKind = userKindCrudService.findById(kindID);
            if (userKind == null)
                throw new Exception();
        } catch (Exception e) {
            //RMQ
            resp.sendRedirect(req.getRequestURI());
            return;
        }

        // check correct double input of password
        if (!password.equals(passwordRepeat)) {
            //RMQ
            resp.sendRedirect(req.getRequestURI());
            return;
        }

        if(!ssoManager.register(email,password,userKind))
        {
            resp.sendRedirect(req.getRequestURI());
            return;
        }

        // go to login page
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
