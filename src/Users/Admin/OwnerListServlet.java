package Users.Admin;

import DataBaseAcces.CrudServices.ComplaintCrudService;
import DataBaseAcces.CrudServices.UserCrudService;
import DataBaseAcces.CrudServices.UserFormCrudService;
import DataBaseAcces.CrudServices.UserKindCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserKind;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет списка владельцев
 */

@WebServlet(name = "OwnerListServlet", urlPatterns = {"/admin/owners"})
public class OwnerListServlet extends HttpServlet {
    @EJB
    private UserKindCrudService userKindCrudService;
    @EJB
    private UserCrudService userCrudService;
    @EJB
    private ComplaintCrudService complaintCrudService;
    @EJB
    private UserFormCrudService userFormCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OwnerStruct> owners = getOwnerStructs();
        req.setAttribute("owners", owners);
        req.getRequestDispatcher("/Users/Admin/JSP/Owners.jsp").forward(req,resp);
    }

    private List<OwnerStruct> getOwnerStructs()
    {
        List<OwnerStruct> ownerStructs = new ArrayList<>();
        UserKind userKind = userKindCrudService.findByName("Owner");
        List<User> owners = userCrudService.findByEmailLikeAndKind("",userKind);
        for (User owner : owners) {
            int complaintsCount = complaintCrudService.findByUser(owner).size();
            int formsCount = userFormCrudService.findByUser(owner).size();
            ownerStructs.add(new OwnerStruct(owner, complaintsCount,formsCount));
        }

        return ownerStructs;
    }
}
