package org.lab2;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CartTest{

    @Test
    public void testAddBook() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", 3, "Man", "testAddress");
        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        customer.getCart().addBook(book, 3);

        Map<Book, Integer> expectedItems = new HashMap<>();
        expectedItems.put(book, 3);
        assertEquals(expectedItems, customer.getCart().getItems());
    }
    @Test
    public void testRemoveBook() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", 3, "Man", "testAddress");
        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        customer.getCart().addBook(book, 3);

        customer.getCart().removeBook(book, 2);
        Map<Book, Integer> expectedItems = new HashMap<>();
        expectedItems.put(book, 1);
        assertEquals(expectedItems, customer.getCart().getItems());

    }
    @Test
    public void testClearCart() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", 3, "Man", "testAddress");
        Book book1 = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        customer.getCart().addBook(book1, 3);
        customer.getCart().addBook(book2, 8);

        customer.getCart().clearCart();

        assertTrue(customer.getCart().getItems().isEmpty());

    }

    @Test
    public void testCalculateTotal() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", 3, "Man", "testAddress");
        Book book = new Book(10111,"SYSC","Carleton","Carleton",1,2);
        Book book2 = new Book(10112,"COMP", "Carleton", "Carleton",1, 3);
        customer.getCart().addBook(book,1);
        customer.getCart().addBook(book2,1);
        assertEquals(5, customer.getCart().calculateTotal(), 0.001);
    }
}