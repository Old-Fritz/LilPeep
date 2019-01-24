package ExternalServices.Rabbit;

import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;

public class RabbitFilter extends HttpFilter {

    @EJB
    RabbitSender sender;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        if (sender.getSessionId().equals("")) {
            sender.init(CockieUtils.getSessionCookie(req, resp).getValue());
        }
    }
}
