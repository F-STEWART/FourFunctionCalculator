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
    
    public static void main(String[] args) {
        Evaluator test = new Evaluator();
        Queue<String> input = new ArrayDeque<>();
        
        input = test.toPostFix(input);
        
        test.
    }
    
}
