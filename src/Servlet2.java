import CrudServices.UserKindCrudService;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet2", urlPatterns = {"/servlet"})
public class Servlet2 extends HttpServlet {
    @EJB
    private UserKindCrudService userKindCrudService;

    @EJB
    private SSOManager ssoManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ssoManager.login("qwe@mail.ru", "abcd", userKindCrudService.findById(12));
    }
}
