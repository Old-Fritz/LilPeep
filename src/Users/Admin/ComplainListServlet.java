package Users.Admin;

import DataBaseAcces.CrudServices.ComplaintCrudService;
import DataBaseAcces.Entities.Complaint;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *Сервлет жалобы
 */

@WebServlet(name = "ComplainListServlet", urlPatterns = {"/admin/complains"})
public class ComplainListServlet extends HttpServlet{
    @EJB
    private ComplaintCrudService complaintCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show page with list
        List<Complaint> complaints = complaintCrudService.findAll();
        req.setAttribute("complaints", complaints);
        req.getRequestDispatcher("/Users/Admin/JSP/Complains.jsp").forward(req,resp);
    }

}
