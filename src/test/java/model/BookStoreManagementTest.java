package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookStoreManagementTest {

    @Test
    public void addBookTest() {
        BookStoreManagement bookstore = new BookStoreManagement();

        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128, ":D", "Hamza Zafar", "Carleton", 10,1.99);

        bookstore.addBook(book);
        bookstore.addBook(book2);
        assertEquals(2, bookstore.getBookList().size());
    }

    @Test
    public void updateQuantityTestPositive() {
        BookStoreManagement bookstore = new BookStoreManagement();
        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        bookstore.addBook(book);
        bookstore.updateQuantity(123, 20);

        assertEquals(30, book.getQuantity());
    }
    @Test
    public void updateQuantityTestNegative() {
        BookStoreManagement bookstore = new BookStoreManagement();
        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        bookstore.addBook(book);
        bookstore.updateQuantity(123, -3);
        bookstore.updateQuantity(123, 0);
        assertEquals(10, book.getQuantity());
    }

    @Test
    public void removeBookTest() {
        BookStoreManagement bookstore = new BookStoreManagement();

        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128, ":D", "Hamza Zafar", "Carleton", 10,1.99);
        Book book3 = new Book(125, ":D", "Hamza Zafar", "Carleton", 10,1.99);
        Book book4 = new Book(120, ":D", "Hamza Zafar", "Carleton", 10,1.99);

        bookstore.addBook(book);
        bookstore.addBook(book2);
        bookstore.addBook(book3);
        bookstore.addBook(book4);

        assertTrue(bookstore.getBookList().contains(book4));

        bookstore.removeBook(120);

        assertFalse(bookstore.getBookList().contains(book4));
    }
}
