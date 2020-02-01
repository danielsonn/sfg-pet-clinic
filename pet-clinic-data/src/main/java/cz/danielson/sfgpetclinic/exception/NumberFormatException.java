package cz.danielson.sfgpetclinic.exception;

public class NumberFormatException extends RuntimeException {

    public NumberFormatException() {
        super();
    }

    public NumberFormatException(String message) {
        super(message);
    }

    public NumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
