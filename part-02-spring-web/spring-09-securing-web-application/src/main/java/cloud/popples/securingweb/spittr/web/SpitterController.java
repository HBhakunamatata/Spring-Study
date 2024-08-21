package cloud.popples.securingweb.spittr.web;

import cloud.popples.securingweb.spittr.pojo.Spitter;
import cloud.popples.securingweb.spittr.repository.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String processRegistration(
//            @RequestPart("profilePicture") MultipartFile profilePicture,
            @Valid Spitter spitter,  BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            return "registerForm";
        }
        Spitter saved = repository.save(spitter);

        model.addAttribute("username", saved.getUsername());
        model.addAttribute("id", saved.getId());

        model.addFlashAttribute("spitter", saved);

        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile (
            @PathVariable(value = "username") String username, Model model) {
        if (!model.containsAttribute("spitter")) {
            Spitter spitter = repository.findByUsername(username);
            model.addAttribute(spitter);
        }
        return "profile";
    }

}
