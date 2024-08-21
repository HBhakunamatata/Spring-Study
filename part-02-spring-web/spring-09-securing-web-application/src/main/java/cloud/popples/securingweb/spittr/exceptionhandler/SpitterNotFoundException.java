package cloud.popples.securingweb.spittr.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spitter Not Found")
public class SpitterNotFoundException extends RuntimeException {
}
