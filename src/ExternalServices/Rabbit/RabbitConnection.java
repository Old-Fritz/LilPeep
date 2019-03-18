package ExternalServices.Rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.identity.shared.encode.CookieUtils;

import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Local
@Stateful
public class RabbitConnection {
    private String QUEUE_OUT = null;
    private String QUEUE_ERR = null;

    private String sessionID = "";

    private Channel channel;

    RabbitConnection()
    {

    }

    RabbitConnection(HttpServletRequest req)
    {
        String newSessionID = req.getSession().getId();
        if(this.sessionID.equals(newSessionID))
            //Переинициализировать не надо
            return;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        try{
            Connection connection = factory.newConnection();
            this.sessionID = newSessionID;
            QUEUE_OUT = "out"+sessionID;
            QUEUE_ERR = "err"+sessionID;
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_OUT,false, false, false, null);
            channel.queueDeclare(QUEUE_ERR,false, false, false, null);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOut(String msg) {
        try {
            channel.basicPublish("", QUEUE_OUT, null, msg.getBytes());
        } catch (Exception e) {
        }
    }

    /**
     * Вывод сообщения в поток ошибок
     * @param msg сообщение
     */
    public void sendErr(String msg) {
        try {
            channel.basicPublish("", QUEUE_ERR, null, msg.getBytes());
        } catch (Exception e) {
        }
    }

    @PreDestroy
    protected void onDestroy() {
        try {
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
