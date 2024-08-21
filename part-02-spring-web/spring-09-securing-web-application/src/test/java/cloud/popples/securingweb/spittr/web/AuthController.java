package cloud.popples.securingweb.spittr.web;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class AuthController {

    @RequestMapping(value = "/login", method = GET)
    public String login() {
        return "login";
    }
}
