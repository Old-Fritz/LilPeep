package ExternalServices.Rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.sun.deploy.net.HttpRequest;

import javax.annotation.PreDestroy;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeoutException;


/**
 * Класс, реализующий функции отправки сообщений RabbitMQ
 */
@Local
@Stateless
public class RabbitSender {



    public RabbitSender() {
    }

    /**
     * Вывод сообщения в поток вывода
     * @param msg сообщение
     */
    public void sendOut(HttpServletRequest req, String msg) {
        RabbitConnection connection = getConnection(req);
        try {
            connection.sendOut(msg);
        } catch (Exception e) {
        }

    }

    /**
     * Вывод сообщения в поток ошибок
     * @param msg сообщение
     */
    public void sendErr(HttpServletRequest req, String msg) {
        RabbitConnection connection = getConnection(req);
        try {
            connection.sendErr(msg);
        } catch (Exception e) {
        }

    }

    private RabbitConnection getConnection(HttpServletRequest req)
    {
        RabbitConnection connection = (RabbitConnection)req.getSession().getAttribute("RabbitConnection");
        if(connection==null)
        {
            connection = new RabbitConnection(req);
            req.getSession().setAttribute("RabbitConnection",connection);
        }

        return connection;
    }
}
