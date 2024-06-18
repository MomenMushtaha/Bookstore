package model;

import entity.Book;
import entity.Cart;
import entity.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the functionality of the Customer class.
 * It includes tests for the purchase history of a customer.
 */
public class CustomerTest {

    /**
     * This test checks the functionality of the purchase history of a customer.
     * It creates a test customer and two test books.
     * It then adds these books to the customer's purchase history and checks if the size of the purchase history is as expected.
     * It also prints out the purchase history after each addition.
     */
    @Test
    public void testCustomerPurchaseHistory(){

        // Create a test customer and two test books
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book1 = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128,1, ":D", "Hamza Zafar", "Carleton", 10,1.99);

        // Add the first book to the customer's purchase history and check if the size of the purchase history is 1
        customer.addToPurchaseHistory(book1);
        assertEquals(1, customer.getPurchaseHistory().size());

        // Print out the purchase history
        customer.printOutPurchaseHistory();

        // Add the second book to the customer's purchase history and check if the size of the purchase history is 2
        customer.addToPurchaseHistory(book2);
        assertEquals(2, customer.getPurchaseHistory().size());

        // Print out the purchase history
        customer.printOutPurchaseHistory();
    }
}