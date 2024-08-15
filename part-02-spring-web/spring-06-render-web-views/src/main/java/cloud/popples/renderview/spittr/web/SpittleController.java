package cloud.popples.renderview.spittr.web;

import cloud.popples.renderview.spittr.pojo.Spittle;
import cloud.popples.renderview.spittr.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping("/")
@Controller
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

    private final SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(value = "/recentspittles", method = GET)
    public List<Spittle> spittleList(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count
    ) {
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(value = "/spittles/{spittleId}", method = GET)
    public Spittle spittle(@PathVariable(value = "spittleId") Long id,
                           Model model) {
//        model.addAttribute("spittle", spittleRepository.findOne(id));
//        return "spittle";
        return spittleRepository.findOne(id);
    }

    @RequestMapping(value = "spittle/register", method = GET)
    public String registerSpittle() {
        return "registerForm";
    }
}
