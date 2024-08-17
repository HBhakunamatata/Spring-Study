package cloud.popples.advancedspringmvc.spittr.web;

import cloud.popples.advancedspringmvc.spittr.exceptionhandler.SpittleDuplicateException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Configuration
@ControllerAdvice
public class WebWideExceptionHandler {
    @ExceptionHandler(SpittleDuplicateException.class)
    public String handleException(final Exception ex, final WebRequest request) {
        return "error/duplicate";
    }
}
