package com.finleystewart.fourfunctioncalculator.business;

import static com.finleystewart.fourfunctioncalculator.business.Constants.operators;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

/**
 *
 * @author 1633143
 */
public class Evaluator {
    
    private Deque<String> stack = new ArrayDeque<>();

    private Queue<String> postFixQueue = new ArrayDeque<>();
    
    public Queue<String> toPostFix(Queue<String> input) {
        
        stack.clear();
        postFixQueue.clear();
        
        while (!input.isEmpty()) {
            // Send digits straight to the queue
            if(isDigit(input.peek())) {
                postFixQueue.offer(input.poll());
            } else if (isOperator(input.peek())) {
                // If operator is not greater than the one below it, send that lower one to the queue
                while(compareOperators(input.peek(), stack.peek()) != 1) {
                    postFixQueue.offer(stack.pop());
                }
                stack.push(input.poll());
            }
        }
        
        // Send operators remaining in the stack to the queue
        while(!stack.isEmpty()) {
            postFixQueue.offer(stack.pop());
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
        for(int i=0;i<operators.length;i++) {
            if(op.length() == 1 && operators[i].equals(op)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isDigit(String op) {
        if(op.length() == 1 && Character.isDigit(op.toCharArray()[0])) {
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
