package Exceptions;

/**
 * An Exception for my four function calculator
 *
 * @author Finley
 */
public class NonBinaryInputException extends IllegalArgumentException {
    public NonBinaryInputException() {
        super();
    }
    public NonBinaryInputException(String message) {
        super(message);
    }
}
