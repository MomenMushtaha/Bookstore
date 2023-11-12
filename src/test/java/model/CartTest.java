package model;



import org.junit.Test;
import static org.junit.Assert.*;

public class CartTest{

    @Test
    public void testAddBook() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        customer.getCart().addBook(book);

        assertTrue(customer.getCart().getItems().contains(book));
        assertEquals(9, book.getQuantity());
    }
    @Test
    public void testRemoveBook() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        customer.getCart().addBook(book);

        customer.getCart().removeBook(book);
        assertTrue(customer.getCart().getItems().isEmpty());
        assertEquals(10, book.getQuantity());

    }
    @Test
    public void testClearCart() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book1 = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        customer.getCart().addBook(book1);
        customer.getCart().addBook(book2);

        customer.getCart().clearCart();

        assertTrue(customer.getCart().getItems().isEmpty());
        assertEquals(10, book1.getQuantity());
        assertEquals(10, book2.getQuantity());

    }

    @Test
    public void testCalculateTotal() {
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book = new Book(10111,"SYSC","Carleton","Carleton",1,2);
        Book book2 = new Book(10112,"COMP", "Carleton", "Carleton",1, 3);
        customer.getCart().addBook(book);
        customer.getCart().addBook(book2);
        assertEquals(5, customer.getCart().calculateTotal(), 0.001);
    }
}