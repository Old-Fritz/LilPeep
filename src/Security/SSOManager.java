package Security;

import CrudServices.UserCrudService;
import Entities.User;
import Entities.UserKind;
import com.iplanet.sso.SSOToken;
import com.iplanet.sso.SSOTokenManager;
import com.sun.identity.agents.arch.AgentConfiguration;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Stateless
@Local
public class SSOManager {

    @EJB
    private UserCrudService userCrudService;

    @EJB
    private OpenAM openAM;

    private String tokenCookieName = "iPlanetDirectoryPro";

    public SSOManager()
    {
    }

    public User getCurrentUser(HttpServletRequest req)
    {
        String ssoToken = getSSOToken(req.getCookies());
        long userID = openAM.getUserID(ssoToken);
        return userCrudService.findById(userID);
    }

    public boolean login(HttpServletResponse resp, String email, String password, UserKind userKind)
    {
        User user = userCrudService.getByEmailAndKind(email,userKind);
        if(user == null)
        {
            //RMQ
            return false;
        }

        if(!user.getPassword().equals(password))
        {
            //RMQ
            return false;
        }

        String SSOTokenId = openAM.login(user);
        if(SSOTokenId==null)
        {
            //RMQ
            return false;
        }

        resp.addCookie(new Cookie(tokenCookieName, SSOTokenId));
        return true;
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp)
    {
        User user = getCurrentUser(req);
        if(user != null)
            openAM.logout(user);
        Cookie delCookie = deleteSSOCookie(req.getCookies());
        if(delCookie!=null)
            resp.addCookie(delCookie);
    }

    public boolean register(String email, String password, UserKind userKind)
    {
        User user = userCrudService.getByEmailAndKind(email,userKind);
        // can't register existed user
        if(user!=null)
        {
            // RMQ
            return false;
        }

        user = new User(email,password,userKind);
        try{
            userCrudService.save(user);
        }
        catch (Exception e)
        {
            // RMQ
            return false;
        }

        return true;
    }

    private String getSSOToken(Cookie[] cookies)
    {
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(tokenCookieName))
                return cookie.getValue();
        }

        return null;
    }

    private Cookie deleteSSOCookie(Cookie[] cookies)
    {
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(tokenCookieName))
            {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                return cookie;
            }
        }
        return null;
    }
}