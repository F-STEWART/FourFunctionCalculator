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
 *  A test class for my evaluator
 *
 * @author 1633143
 */
@RunWith(Parameterized.class)
public class EvaluatorTests {
    
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
            {new ArrayDeque<>(Arrays.asList("(","(","1","+","2",")","*","3","-","4",")","5")), "25.0"},
            {new ArrayDeque<>(Arrays.asList("5","*","5","-","6","*","2","/","(","9","-","6",")")), "21.0"},
            {new ArrayDeque<>(Arrays.asList("2","*","(","3","+","3","+","3","+","4","/","1",")")), "26.0"},
            {new ArrayDeque<>(Arrays.asList("5","*","6","-","6","*","2","*","4","+","1","-","7")), "-24.0"},
            {new ArrayDeque<>(Arrays.asList("2","(","9","+","-8",")","*","6","-","3","/","2")), "10.5"},
            {new ArrayDeque<>(Arrays.asList("1","*","3","*","62","-","105","-","4","*","4","+","1")), "66.0"},
            {new ArrayDeque<>(Arrays.asList("2","-","4","-","7","+","(","4","+","5","+","-6",")")), "-6.0"},
            {new ArrayDeque<>(Arrays.asList("3","+","3","+","7","*","1","/","(","5","-","6",")")), "-1.0"},
            {new ArrayDeque<>(Arrays.asList("4","/","2", "+","(","6","*","2","+","8","*","6",")")), "62.0"},
            {new ArrayDeque<>(Arrays.asList("5","*","1", "-","(","2","*","4","-","9","-","6",")")), "12.0"},
            {new ArrayDeque<>(Arrays.asList("5","+","1","-","9","/","2","*","(","2","+","2",")")), "-12.0"},
            {new ArrayDeque<>(Arrays.asList("1","/","1", "+","(","7","*","78","+","-40","*","6",")")), "307.0"},
            {new ArrayDeque<>(Arrays.asList("5","/","1", "/","2","+","(","4","-","9","-","6",")")), "-8.5"},
            {new ArrayDeque<>(Arrays.asList("(","5","*", "5",")","(","5","*","5",")")), "625.0"},
            {new ArrayDeque<>(Arrays.asList("(","5","*", "5",")","5")), "125.0"}
        });
    }
    
    // A Rule is implemented as a class with methods that are associared
    // with the lifecycle of a unit test. These methods run when required.
    // Avoids the need to cut and paste code into every test method.
    @Rule
    public MethodLogger methodLogger = new MethodLogger();

    private final Evaluator evaluator = new Evaluator();
    private final Queue<String> queue;
    private final String expected;
    
    /**
     * Constructor that receives all the data for each test as defined by a row
     * in the list of parameters
     *
     * @param queue
     * @param expected
     */
    public EvaluatorTests(ArrayDeque queue, String expected) {
        this.queue = queue;
        this.expected = expected;
    }
    
    /**
     *  A test that ensures the accuracy of the evaluator
     */
    @Test
    public void testExpressions() throws InvalidStringException, NonMatchingParenthesisException, DivideByZeroException, NonBinaryInputException {
        assertEquals(expected, evaluator.evaluate(queue));
    }
}
