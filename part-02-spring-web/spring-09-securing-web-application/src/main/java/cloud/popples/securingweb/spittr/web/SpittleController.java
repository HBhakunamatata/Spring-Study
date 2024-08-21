package cloud.popples.securingweb.spittr.web;

import cloud.popples.securingweb.spittr.exceptionhandler.SpittleDuplicateException;
import cloud.popples.securingweb.spittr.pojo.Spittle;
import cloud.popples.securingweb.spittr.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping("/spittles")
@Controller
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

    private final SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(value = "/recent", method = GET)
    public String spittleList(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count,
            Model model
    ) {
        List<Spittle> spittleList = spittleRepository.findSpittles(max, count);
        model.addAttribute("spittleList", spittleList);
        return "spittles";
    }

    @RequestMapping(value = "/{spittleId}", method = GET)
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
