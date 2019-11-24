/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finleystewart.fourfunctioncalculator.business;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author Finley
 */
public class Runner {
    
    public void run() {
        Evaluator test = new Evaluator();
        Queue<String> input = new ArrayDeque<>();
        
        input.offer("(");
        input.offer("(");
        input.offer("1");
        input.offer("+");
        input.offer("2");
        input.offer(")");
        input.offer("*");
        input.offer("3");
        input.offer("-");
        input.offer("4");
        input.offer(")");
        input.offer("*");
        input.offer("5");
        
        test.evaluate(test.toPostFix(input));
    }
    
}
