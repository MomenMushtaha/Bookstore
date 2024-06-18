package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * This class is a JUnit test class for the PaymentProcessor class.
 * It contains methods to test the functionality of the PaymentProcessor class.
 * It also includes setup and teardown methods for the tests.
 *
 * @author Mahtab Ameli
 */
public class PaymentProcessorTest {

    // This ByteArrayOutputStream will capture the output data from System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    // This PrintStream holds the original System.out before it is changed for testing
    private final PrintStream originalOut = System.out;

    /**
     * This method is executed before each test.
     * It sets up the environment for capturing System.out output.
     */
    @Before
    public void setUpStreams() {
        // Redirect System.out to outContent
        System.setOut(new PrintStream(outContent));
    }

    /**
     * This method tests the generateTransactionNumber method of the PaymentProcessor class.
     * It checks if the transaction numbers are incremented correctly.
     */
    @Test
    public void testGenerateTransactionNumber() {
        // Generate two consecutive transaction numbers
        int transactionNumber1 = PaymentProcessor.generateTransactionNumber();
        int transactionNumber2 = PaymentProcessor.generateTransactionNumber();

        // Check if the second transaction number is one more than the first one
        assertEquals(transactionNumber1 + 1, transactionNumber2);
    }

    /**
     * This method is executed after each test.
     * It restores the original System.out after the test.
     */
    @After
    public void restoreStreams() {
        // Restore the original System.out
        System.setOut(originalOut);
    }
}