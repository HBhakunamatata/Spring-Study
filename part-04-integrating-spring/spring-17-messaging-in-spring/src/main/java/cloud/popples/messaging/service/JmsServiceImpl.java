package cloud.popples.messaging.service;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.Message;

public class JmsServiceImpl implements JmsService {

//    @Autowired
    private final JmsOperations jmsOperations;

    public JmsServiceImpl(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void sendMessage(String message) {
        jmsOperations.convertAndSend(message);
    }

//    @Override
//    public void receiveMessage() {
//        Spittle message = (Spittle)jmsOperations.receiveAndConvert();
//        System.out.println(message);
//    }

    @JmsListener(destination = "spitter.alert.queue")
    public void receiveMessage(final Message message) {

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
