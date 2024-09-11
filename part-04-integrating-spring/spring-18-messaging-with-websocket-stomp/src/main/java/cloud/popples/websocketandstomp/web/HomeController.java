package cloud.popples.websocketandstomp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = {"/"})
public class HomeController {

    @RequestMapping(method = GET, value = "websocket")
    public String websocket() {
        return "websocket";
    }

    @RequestMapping(method = GET, value = "stomp")
    public String stomp() {
        return "stomp";
    }
}
