package ExternalServices.Rabbit;

import com.rabbitmq.client.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="RabbitControllerServlet", urlPatterns = {"/rabbit"})
public class RabbitControllerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Channel channel = getChannel();
        if(channel == null)
            return;

        String sessionID = req.getSession().getId();

        String text = checkError(channel, sessionID);
        if(text!=null)
            text=checkMessage(channel,sessionID);

        if(text!= null)
        {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(text);
        }

    }


    private Channel getChannel()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            return channel;
        }catch (Exception e) {
            return null;
        }
    }

    private String checkError(Channel channel, String sessionID) throws IOException
    {
        String text = readQueue(channel, "err"+sessionID);
        return text;
    }

    private String checkMessage(Channel channel, String sessionID) throws IOException
    {
        String text = readQueue(channel, "out"+sessionID);
        return text;
    }

    private String readQueue(Channel channel, String name) throws IOException
    {
        GetResponse response = channel.basicGet(name, false);
        if(response!=null)
            return new String(response.getBody());
        else
            return null;

    }
}
