package Rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Local
@Stateless
public class RabbitSender {

    private final String QUEUE_OUT = "out";
    private final String QUEUE_ERR = "out";

    private Channel channel;

    public RabbitSender() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_OUT,false, false, false, null);
        channel.queueDeclare(QUEUE_ERR,false, false, false, null);
    }

    public void sendOut(String msg) {
        try {
            channel.basicPublish("", QUEUE_OUT, null, msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendErr(String msg) {
        try {
            channel.basicPublish("", QUEUE_ERR, null, msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
