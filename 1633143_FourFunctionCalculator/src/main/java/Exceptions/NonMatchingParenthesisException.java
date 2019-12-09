package Exceptions;

/**
 * An Exception for my four function calculator
 *
 * @author Finley
 */
public class NonMatchingParenthesisException extends IllegalArgumentException {
    public NonMatchingParenthesisException() {
        super();
    }
    public NonMatchingParenthesisException(String message) {
        super(message);
    }
}
