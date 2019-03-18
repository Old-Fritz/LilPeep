import DataBaseAcces.CrudServices.UserCrudService;
import DataBaseAcces.Entities.User;
import ExternalServices.Security.SSOManager;

import javax.ejb.EJB;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет перенаправления, если пользователь случайно забудет слэш
 */
@WebServlet(name = "SecurityFilter")
public class SecurityFilter extends HttpFilter {

    @EJB
    SSOManager ssoManager;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = ssoManager.getCurrentUser(req);
        if(user==null)
        {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }

        // get user folder
        String folder = req.getRequestURI().replace("/LilPeep","");
        int secondSlashInd = folder.indexOf('/',1);
        if(secondSlashInd>0)
            folder = folder.substring(1,secondSlashInd);
        else
            folder = folder.substring(1, folder.length());

        // check correct
        if((folder.equals("user")&&user.getUserKind().getId()==1)
                || (folder.equals("owner")&&user.getUserKind().getId()==2)
                || (folder.equals("admin")&&user.getUserKind().getId()==3))
            chain.doFilter(req, res);
        else
            res.sendRedirect(req.getContextPath()+"/login");
    }
}
