package Users.Admin;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
import DataBaseAcces.Entities.DocumentKind;

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
@WebServlet(name="AddDocumentServlet", urlPatterns = {"/admin/addDocument"})
public class AddDocumentServlet extends HttpServlet {
    @EJB
    private DocumentKindCrudService documentKindCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DocumentKind documentKind = new DocumentKind(0,"","",documentKindCrudService.findAll().size(), null);
        req.getSession().setAttribute("newDocument", documentKind);
        req.getRequestDispatcher("/Users/Admin/JSP/editDocument").forward(req,resp);
    }
}