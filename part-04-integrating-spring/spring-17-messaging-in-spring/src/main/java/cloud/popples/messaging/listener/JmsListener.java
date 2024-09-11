package cloud.popples.messaging.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


public class JmsListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            try {
                ActiveMQTextMessage message1 = (ActiveMQTextMessage) message;
                String messageText = message1.getText();
                System.out.println("Message received: " + messageText);
            } catch (JMSException e) {
                throw JmsUtils.convertJmsAccessException(e);
            }
        } else {
            throw new IllegalArgumentException("Message is not a TextMessage");
        }
    }
}
