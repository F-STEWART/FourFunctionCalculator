package com.finleystewart.fourfunctioncalculator.business;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

/**
 *
 * @author 1633143
 */
public class Evaluator {
    
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
        
        while (!input.isEmpty()) {
            System.out.println("queue poll: " + input.poll());
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
