package com.finleystewart.fourfunctioncalculator.business;

import Exceptions.DivideByZeroException;
import Exceptions.InvalidStringException;
import Exceptions.NonBinaryInputException;
import Exceptions.NonMatchingParenthesisException;
import static com.finleystewart.fourfunctioncalculator.business.Constants.operators;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1633143
 */
public class Validator {
    private final static Logger LOG = LoggerFactory.getLogger(Evaluator.class);
    
    public void Validate(Queue<String> input) throws InvalidStringException, NonMatchingParenthesisException, DivideByZeroException, NonBinaryInputException {
        if(isOperator(input.peek())) {
            throw new IllegalArgumentException("Input queue cannot begin with an operator");
        }
        String last = "";
        for (String element : input) {
            // Validate the elements
            if( !( isDouble(element) || isOperator(element) || isBracket(element) ) ) {
                throw new InvalidStringException("Input queue has invalid elements");
            }
            if(last.equals("/") && element.equals("0")) { // This could probably be better if the divider (shoould it be an expression) was evaluated so you could never divide by zero
                throw new DivideByZeroException("Input queue has division by zero");
            }
            if( !isBinaryExpression(input) ) {
                throw new NonBinaryInputException("Input queue is not binary");
            }
            if(!bracketsClosed(input)) {
                throw new NonMatchingParenthesisException("Input queue has improper brackets");
            }
            last = element;
        }
    }
    
    private boolean isBinaryExpression(Queue<String> input) {
        String last = "";
        String beforeLast = "";
        for (String element : input) {
            if(isOperator(last) && ( !(isDouble(beforeLast) || beforeLast.equals(")")) || !(isDouble(element) || element.equals("(")) ) ) {
                LOG.debug("beforeLast: " + beforeLast);
                LOG.debug("last: " + last);
                LOG.debug("element: " + element);
                return false;
            }
            beforeLast = last;
            last = element;
        }
        return true;
    }
    
    private boolean isDifferent(String last, String current) {
        if(isOperator(last) && isOperator(current)) {
            return false;
        }
        if(isDouble(last) && isDouble(current)) {
            return false;
        }
        return true;
    }
    
    private boolean bracketsClosed(Queue<String> input) {
        int openedCount = 0;
        int closedCount = 0;
        for (String element : input) {
            if(element.equals("(")) {
                openedCount++;
            }
            if(element.equals(")")) {
                closedCount++;
            }
        }
        if(openedCount == closedCount) {
            return true;
        }
        return false;
    }
    
    public boolean isBracket(String element) {
        if(element.equals("(") || element.equals(")")) {
            return true;
        }
        return false;
    }
    
    public boolean isDouble(String maybe) {
        try {
            Double tester = Double.parseDouble(maybe);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
            
    public boolean isOperator(String op) {
        for (String operator : operators) {
            if (op.length() == 1 && operator.equals(op)) {
                return true;
            }
        }
        return false;
    }
}
