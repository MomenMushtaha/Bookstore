package model;

import entity.Book;
import entity.BookStoreManagement;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the functionality of the BookStoreManagement class.
 */
public class BookStoreManagementTest {

    /**
     * This test checks if the addBook method correctly adds books to the bookstore.
     */
    @Test
    public void addBookTest() {
        // Create a new bookstore
        BookStoreManagement bookstore = new BookStoreManagement();

        // Create two new books
        Book book = new Book(123, 1,"TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128, 1,":D", "Hamza Zafar", "Carleton", 10,1.99);

        // Add the books to the bookstore
        bookstore.addBook(book);
        bookstore.addBook(book2);

        // Check if the books were added correctly
        assertEquals(2, bookstore.getBookList().size());

        // Clean up after the test
        bookstore.removeBook(book.getIsbn());
        bookstore.removeBook(book2.getIsbn());
        assertEquals(0, bookstore.getBookList().size());
    }

    /**
     * This test checks if the updateQuantity method correctly updates the quantity of a book in the bookstore.
     */
    @Test
    public void updateQuantityTestPositive() {
        // Create a new bookstore and a new book
        BookStoreManagement bookstore = new BookStoreManagement();
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // Add the book to the bookstore and update its quantity
        bookstore.addBook(book);
        bookstore.updateQuantity(book.getIsbn(), 20);

        // Check if the quantity was updated correctly
        assertEquals(30, book.getQuantity());

        // Clean up after the test
        bookstore.removeBook(book.getIsbn());
        assertEquals(0, bookstore.getBookList().size());
    }

    /**
     * This test checks if the updateQuantity method correctly handles negative and zero quantities.
     */
    @Test
    public void updateQuantityTestNegative() {
        // Create a new bookstore and a new book
        BookStoreManagement bookstore = new BookStoreManagement();
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // Add the book to the bookstore and try to update its quantity with negative and zero values
        bookstore.addBook(book);
        bookstore.updateQuantity(123, -3);
        bookstore.updateQuantity(123, 0);

        // Check if the quantity remained the same
        assertEquals(10, book.getQuantity());

        // Clean up after the test
        bookstore.removeBook(book.getIsbn());
        assertEquals(0, bookstore.getBookList().size());
    }

    /**
     * This test checks if the removeBook method correctly removes a book from the bookstore.
     */
    @Test
    public void removeBookTest() {
        // Create a new bookstore
        BookStoreManagement bookstore = new BookStoreManagement();

        // Create four new books
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128, 1,":D", "Hamza Zafar", "Carleton", 10,1.99);
        Book book3 = new Book(125,1, ":D", "Hamza Zafar", "Carleton", 10,1.99);
        Book book4 = new Book(120,1, ":D", "Hamza Zafar", "Carleton", 10,1.99);

        // Add the books to the bookstore
        bookstore.addBook(book);
        bookstore.addBook(book2);
        bookstore.addBook(book3);
        bookstore.addBook(book4);

        // Check if the books were added correctly
        assertTrue(bookstore.getBookList().contains(book4));
        assertEquals(bookstore.getBookList().size(),4);

        // Remove a book from the bookstore
        bookstore.removeBook(book4.getIsbn());

        // Check if the book was removed correctly
        assertEquals(bookstore.getBookList().size(),3);

        // Clean up after the test
        bookstore.removeBook(book.getIsbn());
        bookstore.removeBook(book2.getIsbn());
        bookstore.removeBook(book3.getIsbn());
        bookstore.removeBook(book4.getIsbn());
        assertEquals(0, bookstore.getBookList().size());
    }
}
