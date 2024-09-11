package cloud.popples.messaging.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.nio.charset.StandardCharsets;

public class AmqpListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String s = new String(message.getBody(), StandardCharsets.US_ASCII);
        System.out.println(s);
    }
}
