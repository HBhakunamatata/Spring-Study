package cloud.popples.messaging.service;

import cloud.popples.messaging.domain.Spittle;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitOperations;

import java.nio.charset.StandardCharsets;

public class AmqpServiceImpl implements AmqpService{

    private final RabbitOperations rabbitOperations;

    private final static String EXCHANGE_NAME = "spittr.exchange.direct";

    private final static String ROUTING_KEY = "spittr.routingkey.test";

    private final static String QUEUE_NAME = "spittr.queue.test";

    public AmqpServiceImpl(RabbitOperations rabbitOperations) {
        this.rabbitOperations = rabbitOperations;
    }

    @Override
    public void sendMessage(String message) {
        rabbitOperations.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
    }

//    @Override
//    public String receiveMessage() {
//        String s = (String) rabbitOperations.receiveAndConvert(QUEUE_NAME);
//        return s;
//    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(QUEUE_NAME),
            exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.DIRECT), key = ROUTING_KEY)
    )
    public void receiveMessage(Message message) {
        String s = new String(message.getBody(), StandardCharsets.US_ASCII);
        System.out.println(s);
    }


}
