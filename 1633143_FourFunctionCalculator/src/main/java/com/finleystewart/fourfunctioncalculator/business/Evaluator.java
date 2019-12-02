package com.finleystewart.fourfunctioncalculator.business;

import static com.finleystewart.fourfunctioncalculator.business.Constants.operators;
import com.finleystewart.fourfunctioncalculator.business.Validator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  A class that evaluates mathematical expressions using the proper order of operations
 *
 * @author 1633143
 */
public class Evaluator {
    
    private final static Logger LOG = LoggerFactory.getLogger(Evaluator.class);
    
    private Validator validator = new Validator();
    private Deque<String> stack = new ArrayDeque<>();
    
    private Queue<String> toPostFix(Queue<String> input) {
        
        stack.clear();
        Queue<String> postFixQueue = new ArrayDeque<>();
        
        while (!input.isEmpty()) {
            // Send digits straight to the queue
            if(validator.isDouble(input.peek())) {
                LOG.info("Found digit: " + input.peek());
                postFixQueue.offer(input.poll());
            } else if (validator.isOperator(input.peek())) {
                LOG.info("Found operator: " + input.peek());
                // If operator is not greater than the one below it, send that lower one to the queue
                while(!stack.isEmpty() &&  !stack.peek().equals("(") && compareOperators(input.peek(), stack.peek()) != 1) {
                    postFixQueue.offer(stack.pop());
                }
                stack.push(input.poll());
            } else if(input.peek().equals("(")) {
                LOG.info("Found: (");
                // Push ( to stack
                stack.push(input.poll());
            } else if(input.peek().equals(")")) {
                LOG.info("Found: )");
                // I assume here that finding a ) means there is a ( somewhere earlier. This will be checked in the validator.
                // Pop stack until the last (
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    postFixQueue.offer(stack.pop());
                }
                // Remove ( so it is not rused
                stack.pop();
                // Remove ) from input queue
                input.poll();
            }
        }
        
        // Send operators remaining in the stack to the queue
        while(!stack.isEmpty()) {
            postFixQueue.offer(stack.pop());
        }
        
        printQueue(postFixQueue);
        
        return postFixQueue;
    }
    
    private double evaluatePostFix(Queue<String> input) {
        
        stack.clear();
        Deque<String> expressionStack = new ArrayDeque<>();
        
        while (!input.isEmpty()) {
            // Send digits straight to the queue
            if(validator.isDouble(input.peek())) {
                LOG.info("Found digit: " + input.peek());
                stack.push(input.poll());
            } else if (validator.isOperator(input.peek())) {
                expressionStack.push(stack.pop());
                expressionStack.push(input.poll());
                expressionStack.push(stack.pop());
                
                Double value = solve(expressionStack.pop(), expressionStack.pop(), expressionStack.pop());
                
                stack.push(value.toString());
            }
        }
        
        LOG.info("Result: " + stack.peek());
        
        return Double.parseDouble(stack.pop());
    }
    
    public double evaluateInFix(Queue<String> inFixInput) {
        validator.Validate(inFixInput);
        Queue<String> input = toPostFix(inFixInput);
        return evaluatePostFix(input);
    }
    
    private double solve(String x, String op, String y) {
        LOG.info("Operation: " + x + " " + op + " " + y);
        switch(op) {
            case "*":
                return Double.parseDouble(x) * Double.parseDouble(y);
            case "/":
                return Double.parseDouble(x) / Double.parseDouble(y);
            case "+":
                return Double.parseDouble(x) + Double.parseDouble(y);
            case "-":
                return Double.parseDouble(x) - Double.parseDouble(y);
            default:
                throw new IllegalArgumentException("Unrecognised operator");
        }
    }
    
    private int compareOperators(String x, String y) {
        LOG.info("Compare " + x + " to " + y);
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
    
    private boolean isHighPriority(String operator) {
        return operator.equals("*") || operator.equals("/");
    }
    
    private boolean isLowPriority(String operator) {
        return operator.equals("+") || operator.equals("-");
    }
    
    private void printQueue(Queue<String> queue) {
        Iterator i = queue.iterator();
        while(i.hasNext()) {
            LOG.info(i.next().toString());
        }
    }
    
}
