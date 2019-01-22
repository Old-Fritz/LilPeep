package ExternalServices.Rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateful;
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

    private final String QUEUE_OUT = "out"+sessionID;
    private final String QUEUE_ERR = "err"+sessionID;

    private static int sessionID = 0;

    private Channel channel;

    public RabbitSender() {
        /*
        sessionID++;
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
        */
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
        /*
        try {
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        */
    }
}
