package org.lab2;

import junit.framework.TestCase;

public class CartTest extends TestCase {

    public void testAddBook() {
        Cart cart = new Cart();
        Book book = new Book("10111","SYSC","Carleton","Carleton",1,1.99);
        cart.addBook(book,1);
        assertEquals(1,cart.getItems().size());
    }

    public void testRemoveBook() {
        Cart cart = new Cart();
        Book book = new Book("10111","SYSC","Carleton","Carleton",1,1.99);
        cart.addBook(book,2);
        cart.removeBook(book,1);
        assertEquals(1,cart.getItems().size());
    }

    public void testClearCart() {
        Cart cart = new Cart();
        Book book = new Book("10111","SYSC","Carleton","Carleton",1,1.99);
        Book book2 = new Book("10112","COMP", "Carleton", "Carleton",1, 2.99);
        cart.addBook(book,1);
        cart.addBook(book2,1);
        cart.clearCart();
        assertEquals(0,cart.getItems().size());
    }

    public void testCalculateTotal() {
        Cart cart = new Cart();
        Book book = new Book("10111","SYSC","Carleton","Carleton",1,1.99);
        Book book2 = new Book("10112","COMP", "Carleton", "Carleton",1, 2.99);
        cart.addBook(book,1);
        cart.addBook(book2,1);
        assertEquals(4.98,cart.calculateTotal());
    }
}