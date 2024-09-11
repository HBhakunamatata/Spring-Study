package cloud.popples.websocketandstomp.web;

import cloud.popples.websocketandstomp.stomp.Shout;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MarcoController {

    @MessageMapping("/marco") // imply /app/marco
    @SendTo("/topic/shout")
    public Shout handleShout(Shout shout) {
        System.out.println("Recieved Message: " + shout.getMessage());
        Shout outGoing = new Shout("Polo!");
        return outGoing;
    }
}
