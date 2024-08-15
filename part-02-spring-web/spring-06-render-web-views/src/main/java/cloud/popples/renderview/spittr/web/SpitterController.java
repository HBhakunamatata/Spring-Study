package cloud.popples.renderview.spittr.web;

import cloud.popples.renderview.spittr.pojo.Spitter;
import cloud.popples.renderview.spittr.repository.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/spitter")
@Controller
public class SpitterController {

    private final SpitterRepository repository;

    @Autowired
    public SpitterController(SpitterRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        Spitter spitter = new Spitter("", "", "", "", "");
        model.addAttribute("spitter", spitter);
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(@Valid Spitter spitter, BindingResult result) {
        if (result.hasErrors()) {
            return "registerForm";
        }
        repository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile (
            @PathVariable(value = "username") String username, Model model) {
        Spitter spitter = repository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }

}
