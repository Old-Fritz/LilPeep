package ExternalServices.Rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.sun.deploy.net.HttpRequest;

import javax.annotation.PreDestroy;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateful;
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
@Stateful
public class RabbitSender {

    private String QUEUE_OUT = null;
    private String QUEUE_ERR = null;

    private String sessionID = "";

    private Channel channel;

    public RabbitSender() {

    }

    public void init(String sessionID) {
        if(this.sessionID.equals(sessionID))
            //Переинициализировать не надо
            return;
        this.sessionID = sessionID;
        QUEUE_OUT = "out"+sessionID;
        QUEUE_ERR = "err"+sessionID;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(8080);
        try{
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_OUT,false, false, false, null);
            channel.queueDeclare(QUEUE_ERR,false, false, false, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Вывод сообщения в поток вывода
     * @param msg сообщение
     */
    public void sendOut(String msg) {
        /*
        try {
            channel.basicPublish("", QUEUE_OUT, null, msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /**
     * Вывод сообщения в поток ошибок
     * @param msg сообщение
     */
    public void sendErr(String msg) {
        /*
        try {
            channel.basicPublish("", QUEUE_ERR, null, msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    @PreDestroy
    protected void onDestroy() {
        try {
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public String getSessionId(){
        return sessionID;
    }
}
