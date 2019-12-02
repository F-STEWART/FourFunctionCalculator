package com.finleystewart.fourfunctioncalculator.business;

import static com.finleystewart.fourfunctioncalculator.business.Constants.operators;
import java.util.Iterator;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1633143
 */
public class Validator {
    private final static Logger LOG = LoggerFactory.getLogger(Evaluator.class);
    
    public void Validate(Queue<String> input) {
        String last = "";
        for (String element : input) {
            // Validate the elements
            if( !( isDouble(element) || isOperator(element) || isBracket(element) ) ) {
                throw new IllegalArgumentException("Input queue has invalid elements");
            }
            if( (!last.equals("")) && !(isDifferent(last, element)) ) {
                throw new IllegalArgumentException("Input queue has repeat types");
            }
            if(!bracketsClosed(input)) {
                throw new IllegalArgumentException("Input queue has improper brackets");
            }
            last = element;
        }
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
