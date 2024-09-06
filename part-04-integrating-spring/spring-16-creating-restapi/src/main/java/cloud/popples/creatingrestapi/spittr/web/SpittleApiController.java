package cloud.popples.creatingrestapi.spittr.web;

import cloud.popples.creatingrestapi.spittr.domain.Spittle;
import cloud.popples.creatingrestapi.spittr.exception.SpittleNotFoundException;
import cloud.popples.creatingrestapi.spittr.service.SpittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import cloud.popples.creatingrestapi.spittr.exception.Error;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SpittleApiController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    @Autowired
    private SpittleService spittleService;


    @RequestMapping(value = "/spittles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count
    ) {
        return spittleService.findSpittles(max, count);
    }

//    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/spittle/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Spittle> saveSpittle (@RequestBody Spittle spittle, UriComponentsBuilder uriBuilder) {
        Spittle savedSpittle = spittleService.saveSpittle(spittle);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI locationUri = uriBuilder.path("/spittle/{id}").buildAndExpand(savedSpittle.getId()).toUri();
        headers.setLocation(locationUri);

        ResponseEntity<Spittle> response = new ResponseEntity<>(savedSpittle, headers, HttpStatus.CREATED);
        return response;
    }


    @RequestMapping(value = "/spittle/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Spittle findSpittleById(@PathVariable(value = "id") long id) {
        Spittle spittle = spittleService.findSpittleById(id);
        if (spittle == null) {
            throw new SpittleNotFoundException(id);
        }
        return spittle;
    }


    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error spittleNotFound (SpittleNotFoundException exception) {
        long id = exception.getId();
        Error error = new Error(4, "Spittle no." + id + " Not Found");
        return error;
    }





}
