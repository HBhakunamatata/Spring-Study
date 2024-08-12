package cloud.popples.stomp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MacroController {

    private static final Logger logger =
        LoggerFactory.getLogger(MacroController.class);

    @MessageMapping
    public void handleShout (Shout shout) {
        logger.info("Received message: {}", shout.getMessage());
    }
}
