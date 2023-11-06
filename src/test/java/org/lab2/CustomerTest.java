package org.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void testCustomerPurchaseHistory(){

        //Create test Customer and test Books
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", 3, "Man", "testAddress");
        Book book1 = new Book("ISBN123", "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book("ISBN128", ":D", "Hamza Zafar", "Carleton", 10,1.99);

        //Test Adding books to purchaseHistory
        customer.addToPurchaseHistory(book1);
        assertEquals(1, customer.purchaseHistory.size());
        customer.printOutPurchaseHistory();

        customer.addToPurchaseHistory(book2);
        assertEquals(2, customer.purchaseHistory.size());
        customer.printOutPurchaseHistory();

        //Test removing books from purchaseHistory
        customer.removeFromPurchaseHistory(book2);
        assertEquals(1, customer.purchaseHistory.size());

        //Test error message for removing book that is not present
        customer.removeFromPurchaseHistory(book2);

        //Test printing out purchaseHistory after changes
        customer.printOutPurchaseHistory();
        customer.removeFromPurchaseHistory(book1);
        //purchaseHistory should be empty
        assertEquals(0, customer.purchaseHistory.size());
        customer.printOutPurchaseHistory();

    }
}
