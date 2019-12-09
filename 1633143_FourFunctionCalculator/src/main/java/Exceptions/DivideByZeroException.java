package Exceptions;

/**
 * An Exception for my four function calculator
 *
 * @author Finley
 */
public class DivideByZeroException extends IllegalArgumentException {
    public DivideByZeroException() {
        super();
    }
    public DivideByZeroException(String message) {
        super(message);
    }
}
