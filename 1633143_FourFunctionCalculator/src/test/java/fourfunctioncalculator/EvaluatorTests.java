package fourfunctioncalculator;

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
            {new ArrayDeque<>(Arrays.asList("(","(","1","+","2",")","*","3","-","4",")","*","5")), 25},
            {new ArrayDeque<>(Arrays.asList("5","*","5","-","6","*","2","/","(","9","-","6",")")), 21}
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
    public EvaluatorTests(ArrayDeque queue, double expected) {
        this.queue = queue;
        this.expected = expected;
    }
    
    /**
     *  A test that ensures the accuracy of the evaluator
     */
    @Test
    public void testExpressions() {
        assertEquals(expected, evaluator.evaluate(evaluator.toPostFix(queue)), 0.001);
    }
}
