package cloud.popples.creatingrestapi.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spittle Not Found")
public class SpittleNotFoundException extends RuntimeException {

    private long id;

    public SpittleNotFoundException(long id) {
        super("Spittle [" + id + "] Not Found");
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
