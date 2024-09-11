package cloud.popples.messaging;

import cloud.popples.messaging.config.AmqpConfig;
import cloud.popples.messaging.service.AmqpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = AmqpConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AmqpServiceTest {

    @Autowired
    private AmqpService amqpService;

    @Test
    public void testSend() {
        amqpService.sendMessage("{ \"id\": 0, \"message\": \"\", \"time\": \"\", \"latitude\": 0, \"longitude\": 0 }");
    }

//    @Test
//    public void testReceive() {
//        String receiveMessage = amqpService.receiveMessage();
//        System.out.println(receiveMessage);
//    }
}
