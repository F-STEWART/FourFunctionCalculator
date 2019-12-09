package fourfunctioncalculator;

import Exceptions.DivideByZeroException;
import Exceptions.InvalidStringException;
import Exceptions.NonBinaryInputException;
import Exceptions.NonMatchingParenthesisException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Test;

import static org.junit.Assert.*;

import com.finleystewart.fourfunctioncalculator.business.Evaluator;
import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.Rule;

/**
 *  A test class for my validation
 *
 * @author 1633143
 */
@RunWith(Parameterized.class)
public class ValidationTests {
    
    /**
     * A static method is required to hold all the data to be tested and the
     * expected results for each test. This data must be stored in a
     * two-dimension array. The 'name' attribute of Parameters is a JUnit 4.11
     * feature
     *
     * @return The list of queues and expected values
     */
    @Parameters(name = "{index} plan{0}={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // Invalid Strings
            {new ArrayDeque<>(Arrays.asList("(","(","1","+","2",")","*","F","-","4","","*","5")), 25},
            {new ArrayDeque<>(Arrays.asList("5","!","5","-","6","*","2","/","(","9","-","6",")")), 21},
            {new ArrayDeque<>(Arrays.asList("2","*","(","3","+","3","#","(","3","+","4",")")), 26},
            //Non matching parenthesis
            {new ArrayDeque<>(Arrays.asList("(","*","/","-","6","*","2","*","4","+","1","-","7")), -24},
            {new ArrayDeque<>(Arrays.asList("2","(","(","9","+","-8",")","*","-","6","3","3","/","2")), 10.5},
            {new ArrayDeque<>(Arrays.asList("*","3","*","62","-","105","-","4","*","4","+",")")), 66},
            // Non Binary Expressions
            {new ArrayDeque<>(Arrays.asList("2","-","-","-","7","+","(","4","+","5","+","-6",")")), -6},
            {new ArrayDeque<>(Arrays.asList("4","2","2", "+","(","6","*","2","+","8","*",")",")")), 62},
            {new ArrayDeque<>(Arrays.asList("5","*","-", "-","(","2","*","4","-","9","-","6",")")), 12},
            // 
            {new ArrayDeque<>(Arrays.asList("5","*","/","0","+","-6", "/","2","*","4","+","1","-","7")), -24},
            {new ArrayDeque<>(Arrays.asList("2","*","(","9","+","-8",")","*","-","6","3","3","/","0")), 10.5},
            {new ArrayDeque<>(Arrays.asList("3","/", "0","*","62","-","105","-","4","*","4","+","1")), 66}
        });
    }
    
    // A Rule is implemented as a class with methods that are associared
    // with the lifecycle of a unit test. These methods run when required.
    // Avoids the need to cut and paste code into every test method.
    @Rule
    public MethodLogger methodLogger = new MethodLogger();

    private final Evaluator evaluator = new Evaluator();
    private final Queue<String> queue;
    private final double expected;
    
    /**
     * Constructor that receives all the data for each test as defined by a row
     * in the list of parameters
     *
     * @param queue
     * @param expected
     */
    public ValidationTests(ArrayDeque queue, double expected) {
        this.queue = queue;
        this.expected = expected;
    }
    
    /**
     *  A test that ensures the accuracy of the validator
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpressions() {
        assertEquals(expected, evaluator.evaluate(queue), 0.001);
    }
}
