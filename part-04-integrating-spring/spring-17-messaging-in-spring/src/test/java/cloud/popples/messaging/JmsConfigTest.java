package cloud.popples.messaging;

import cloud.popples.messaging.config.JmsConfig;
import cloud.popples.messaging.service.JmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = JmsConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsConfigTest {

    @Autowired
    private JmsService jmsService;

    @Test
    public void testSendAndReceiveString() {
        jmsService.sendMessage("Hello World");
    }

    @Test
    public void testSendAndReceiveJson() {
        jmsService.sendMessage("{ \"id\": 0, \"message\": \"\", \"time\": \"\", \"latitude\": 0, \"longitude\": 0 }");
    }

}
