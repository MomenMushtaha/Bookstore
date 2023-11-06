package org.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookStoreManagementTest {

    @Test
    public void addBookTest() {
        BookStoreManagement bookstore = new BookStoreManagement();

        Book book = new Book("ISBN123", "TEST", "Hamza Zafar", "Carleton", 10);
        Book book2 = new Book("ISBN128", ":D", "Hamza Zafar", "Carleton", 10);

        bookstore.addBook(book);
        bookstore.addBook(book2);
        assertEquals(2, bookstore.getBookList().size());
    }

    @Test
    public void updateQuantityTestPositive() {
        BookStoreManagement bookstore = new BookStoreManagement();
        Book book = new Book("ISBN123", "TEST", "Hamza Zafar", "Carleton", 10);
        bookstore.addBook(book);
        bookstore.updateQuantity("ISBN123", 20);

        assertEquals(30, book.getQuantity());
    }
    @Test
    public void updateQuantityTestNegative() {
        BookStoreManagement bookstore = new BookStoreManagement();
        Book book = new Book("ISBN123", "TEST", "Hamza Zafar", "Carleton", 10);
        bookstore.addBook(book);
        bookstore.updateQuantity("ISBN123", -3);
        bookstore.updateQuantity("ISBN123", 0);
        assertEquals(10, book.getQuantity());
    }

    @Test
    public void removeBookTest() {
        BookStoreManagement bookstore = new BookStoreManagement();

        Book book = new Book("ISBN123", "TEST", "Hamza Zafar", "Carleton", 10);
        Book book2 = new Book("ISBN128", ":D", "Hamza Zafar", "Carleton", 10);
        Book book3 = new Book("ISBN125", ":D", "Hamza Zafar", "Carleton", 10);
        Book book4 = new Book("ISBN120", ":D", "Hamza Zafar", "Carleton", 10);

        bookstore.addBook(book);
        bookstore.addBook(book2);
        bookstore.addBook(book3);
        bookstore.addBook(book4);

        assertTrue(bookstore.getBookList().contains(book4));

        bookstore.removeBook("ISBN120");

        assertFalse(bookstore.getBookList().contains(book4));
    }
}
