package cloud.popples.advancedspringmvc.spittr.web;

import cloud.popples.advancedspringmvc.spittr.exceptionhandler.SpittleDuplicateException;
import cloud.popples.advancedspringmvc.spittr.exceptionhandler.SpittleNotFoundException;
import cloud.popples.advancedspringmvc.spittr.pojo.Spittle;
import cloud.popples.advancedspringmvc.spittr.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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
    public String spittle(@PathVariable(value = "spittleId") Long id,
                           Model model) {
        Spittle spittle = spittleRepository.findOne(id);
        if (spittle == null) {
//            throw new SpittleNotFoundException();
            throw new SpittleDuplicateException();
        }
        model.addAttribute("spittle", spittle);
        return "spittle";
    }

    public String saveSpittle(Spittle spittle, Model model) {
        spittleRepository.save(new Spittle("", new Date(), 0.0, 0.0));
        return "redirect:/recentspittles";
    }


//    @ExceptionHandler(SpittleDuplicateException.class)
//    public String handleDuplicateSpittle() {
//        return "error/duplicate";
//    }

}
