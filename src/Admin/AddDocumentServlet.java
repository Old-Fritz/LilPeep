package Admin;

import CrudServices.DocumentKindCrudService;
import CrudServices.UserFormCrudService;
import Entities.DocumentKind;
import Entities.User;
import Entities.UserForm;
import Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для добавления документа
 */
@WebServlet(name="AddDocumentServlet", urlPatterns = {""})
public class AddDocumentServlet extends HttpServlet {
    @EJB
    DocumentKindCrudService documentKindCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DocumentKind documentKind = new DocumentKind(0,"","",documentKindCrudService.findAll().size(), null);

        req.getSession().setAttribute("newDocument", documentKind);
        req.getRequestDispatcher("/Admin/JSP/editDocument").forward(req,resp);
    }
}
