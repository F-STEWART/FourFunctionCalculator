package com.finleystewart.fourfunctioncalculator.business;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Finley
 */
public class Runner {
    
    private final static Logger LOG = LoggerFactory.getLogger(Evaluator.class);
    
    public void run() {
        Evaluator test = new Evaluator();
        Queue<String> input = new ArrayDeque<>(Arrays.asList("(","(","1","+","2",")","*","3","-","4",")","*",".5"));
        
        test.evaluateInFix(input);
    }
    
}
