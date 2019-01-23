package ExternalServices.Rabbit;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CockieUtils {
    public static Cookie getSessionCookie(HttpServletRequest req, HttpServletResponse resp){
        for (Cookie cookie: req.getCookies()){
            if (cookie.getName().equals("sessionID")) {
                return cookie;
            }
        }
        Cookie cookie=new Cookie("sessionID",String.valueOf((int)(Math.random()*Integer.MAX_VALUE)));
        cookie.setMaxAge(86400);
        resp.addCookie(cookie);
        return cookie;
    }
}
