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
import java.util.List;

/**
 * Сервлет документов администратора
 */
@WebServlet(name = "AdminDocumentsServlet", urlPatterns = {"/admin/", "/admin/documents"})
public class AdminDocumentsServlet extends HttpServlet {
    @EJB
    private DocumentKindCrudService documentKindCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page"))
        {
            req.getRequestDispatcher("/admin/JSP/AdminDocuments.jsp").forward(req,resp);
            return;
        }

        // return only list of documents
        String text = req.getParameter("text");
        if(text==null)
            text="";
        List<DocumentKind> documents = documentKindCrudService.findByName(text);
        req.setAttribute("documents", documents);
        req.getRequestDispatcher("/admin/JSP/AdminDocumentsList.jsp").forward(req,resp);
    }
}
