package Admin;

import CrudServices.ComplaintCrudService;
import CrudServices.UserCrudService;
import CrudServices.UserKindCrudService;
import Entities.Complaint;
import Entities.User;
import Entities.UserKind;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ComplainListServlet", urlPatterns = {"/admin/complains"})
public class ComplainListServlet extends HttpServlet{
    @EJB
    ComplaintCrudService complaintCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show page with list
        List<Complaint> complaints = complaintCrudService.findAll();
        req.setAttribute("complaints", complaints);
        req.getRequestDispatcher("/Admin/JSP/Complains.jsp").forward(req,resp);
    }

}
