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
    
    public boolean isValid(Queue<String> input) {
        for (String element : input) {
            // Validate the elements
            if( !( isDouble(element) || isOperator(element) || element.equals("(") || element.equals(")") ) ) {
                return false;
            }
            
        }
        return true;
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
