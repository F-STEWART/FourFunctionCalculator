package Exceptions;

/**
 * An Exception for my four function calculator
 *
 * @author Finley
 */
public class InvalidStringException extends IllegalArgumentException {
    public InvalidStringException() {
        super();
    }
    public InvalidStringException(String message) {
        super(message);
    }
}
