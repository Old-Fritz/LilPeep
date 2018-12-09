package Admin;

import CrudServices.DocumentKindCrudService;
import CrudServices.UserCrudService;
import CrudServices.UserKindCrudService;
import Entities.DocumentKind;
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

/**
 * Сервлет списка владельцев
 */

@WebServlet(name = "OwnerListServlet", urlPatterns = {"/admin/owners"})
public class OwnerListServlet extends HttpServlet {
    @EJB
    UserKindCrudService userKindCrudService;
    @EJB
    UserCrudService userCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // show simple page without any info
        if(type == null || type.equals("page"))
        {
            req.getRequestDispatcher("/admin/JSP/Owners.jsp").forward(req,resp);
            return;
        }

        // return only list of owners
        String text = req.getParameter("text");
        if(text==null)
            text="";
        UserKind userKind = userKindCrudService.findByName("Owner");
        List<User> owners = userCrudService.findByEmailLikeAndKind(text,userKind);
        req.setAttribute("owners", owners);
        req.getRequestDispatcher("/SimpleUser/JSP/OwnerList.jsp").forward(req,resp);
    }
}
