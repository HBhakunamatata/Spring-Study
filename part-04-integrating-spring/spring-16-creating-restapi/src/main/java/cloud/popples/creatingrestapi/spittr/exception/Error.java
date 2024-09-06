package cloud.popples.creatingrestapi.spittr.exception;

public class Error {

    private final int code;
    private final String message;

    public Error(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
