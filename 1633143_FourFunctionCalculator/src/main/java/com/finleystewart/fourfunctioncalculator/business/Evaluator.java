package com.finleystewart.fourfunctioncalculator.business;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1633143
 */
public class Evaluator {
    
    private final static Logger LOG = LoggerFactory.getLogger(Evaluator.class);
    
    Deque<String> postFixStack = new ArrayDeque<>();

    Queue<String> postFixQueue = new ArrayDeque<>();
    
    ArrayList<String> operators = new ArrayList<>();
    
    public Evaluator() {
        operators.add("*");
        operators.add("/");
        operators.add("+");
        operators.add("-");
    }
    
    public Queue<String> toPostFix(Queue<String> input) {
        
        postFixStack.clear();
        postFixQueue.clear();
        String current = "";
        String top = "";
        
        while (!input.isEmpty()) {
            
            current =  input.poll();
            
            if(isOperator(current)) {
                
                top = postFixStack.peek();
                
                while(!(compareOperators(current, top) == 1) && !postFixStack.isEmpty()) {
                    postFixQueue.offer(postFixStack.pop());
                    top = postFixStack.peek();
                }
                postFixStack.push(current);
                
            } else {
                postFixQueue.offer(current);
            }
        }
        
        return null;
    }
    
    private int compareOperators(String x, String y) {
        if( isHighPriority(x) && isLowPriority(y) ) {
            return 1;
        } else if( isLowPriority(x) && isHighPriority(y) ) {
            return -1;
        } else if ( ( isHighPriority(x) && isHighPriority(y) ) || ( isLowPriority(x) && isLowPriority(y) ) ) {
            return 0;
        } else {
            throw new IllegalArgumentException("operator is not acceptable");
        }
    }
    
    private boolean isOperator(String op) {
        if(operators.contains(op)) {
            return true;
        }
        return false;
    }
    
    private boolean isHighPriority(String operator) {
        if(operator.equals("*") || operator.equals("/")) {
            return true;
        }
        return false;
    }
    
    private boolean isLowPriority(String operator) {
        if(operator.equals("+") || operator.equals("-")) {
            return true;
        }
        return false;
    }
    
}
