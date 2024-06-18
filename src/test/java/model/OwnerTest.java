package model;

import entity.Book;
import entity.BookStoreManagement;
import entity.Owner;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the functionality of the Owner class.
 * It includes methods to test adding, removing, and updating books in the bookstore.
 */
public class OwnerTest {

    /**
     * This method tests the addBookToStore method of the Owner class.
     * It checks if books are added correctly to the bookstore.
     */
    @Test
    public void addBookToStoreTest() {
        // Initialization of test objects
        BookStoreManagement bookstore = new BookStoreManagement();
        Owner testOwner = new Owner("owneremail", "12345", "Owner", "ImTheBoss", "Boss", "bossstreet");
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128,1, ":D", "Hamza Zafar", "Carleton", 10,1.99);

        // Testing the addition of books to the bookstore
        testOwner.setOwnersStore(bookstore);
        testOwner.addBookToStore(book);
        testOwner.addBookToStore(book2);
        assertEquals(2, testOwner.getBookStore().size());

        // Cleanup after test
        testOwner.removeBookFromStore(book);
        testOwner.removeBookFromStore(book2);
        assertEquals(0, testOwner.getBookStore().size());
    }

    /**
     * This method tests the removeBookFromStore method of the Owner class.
     * It checks if books are removed correctly from the bookstore.
     */
    @Test
    public void removeBookFromStoreTest(){
        // Initialization of test objects
        BookStoreManagement bookstore = new BookStoreManagement();
        Owner testOwner = new Owner("owneremail", "12345", "Owner", "ImTheBoss", "Boss", "bossstreet");
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128, 1,":D", "Hamza Zafar", "Carleton", 10,1.99);

        // Testing the removal of books from the bookstore
        testOwner.setOwnersStore(bookstore);
        testOwner.addBookToStore(book);
        testOwner.addBookToStore(book2);
        assertEquals(2, testOwner.getBookStore().size());
        testOwner.removeBookFromStore(book);
        assertEquals(1, testOwner.getBookStore().size());
        testOwner.removeBookFromStore(book2);
        assertEquals(0, testOwner.getBookStore().size());
    }

    /**
     * This method tests the updateStoreQuantity method of the Owner class.
     * It checks if the quantity of books in the bookstore is updated correctly.
     */
    @Test
    public void updateStoreQuantityTest(){
        // Initialization of test objects
        BookStoreManagement bookstore = new BookStoreManagement();
        Owner testOwner = new Owner("owneremail", "12345", "Owner", "ImTheBoss", "Boss", "bossstreet");
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // Testing the update of book quantity in the bookstore
        testOwner.setOwnersStore(bookstore);
        testOwner.addBookToStore(book);
        testOwner.updateStoreQuantity(123, -3);
        testOwner.updateStoreQuantity(128, 0);
        assertEquals(10, book.getQuantity());

        // Cleanup after test
        testOwner.removeBookFromStore(book);
        assertEquals(0, testOwner.getBookStore().size());
    }
}