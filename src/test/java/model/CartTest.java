package model;

import entity.Book;
import entity.Cart;
import entity.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class is used to test the functionality of the Cart class.
 */
public class CartTest{

    /**
     * Test case for adding a book to the cart.
     */
    @Test
    public void testAddBook() {
        // Create a new customer and a new book
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // Create a new cart and set it to the customer
        Cart cart = new Cart();
        customer.setCart(cart);

        // Add the book to the customer's cart
        customer.getCart().addBook(book);

        // Assert that the book was added to the cart
        assertTrue(customer.getCart().getItems().contains(book));
    }

    /**
     * Test case for removing a book from the cart.
     */
    @Test
    public void testRemoveBook() {
        // Create a new customer and a new book
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // Create a new cart and set it to the customer
        Cart cart = new Cart();
        customer.setCart(cart);

        // Add the book to the customer's cart
        customer.getCart().addBook(book);

        // Remove the book from the customer's cart
        customer.getCart().removeBook(book);

        // Assert that the cart is empty
        assertTrue(customer.getCart().getItems().isEmpty());
    }

    /**
     * Test case for clearing the cart.
     */
    @Test
    public void testClearCart() {
        // Create a new customer and two new books
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book1 = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // Create a new cart and set it to the customer
        Cart cart = new Cart();
        customer.setCart(cart);

        // Add the books to the customer's cart
        customer.getCart().addBook(book1);
        customer.getCart().addBook(book2);

        // Clear the customer's cart
        customer.getCart().clearCart();

        // Assert that the cart is empty
        assertTrue(customer.getCart().getItems().isEmpty());
    }

    /**
     * Test case for calculating the total price of the cart.
     */
    @Test
    public void testCalculateTotal() {
        // Create a new customer and two new books
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book = new Book(10111,1,"SYSC","Carleton","Carleton",1,2);
        Book book2 = new Book(10112,1,"COMP", "Carleton", "Carleton",1, 3);

        // Create a new cart and set it to the customer
        Cart cart = new Cart();
        customer.setCart(cart);

        // Add the books to the customer's cart
        customer.getCart().addBook(book);
        customer.getCart().addBook(book2);

        // Assert that the total price of the cart is correct
        assertEquals(5, customer.getCart().calculateTotal(), 0.001);
    }

    /**
     * Test case for checking out the cart.
     */
    @Test
    public void testCheckout(){
        // Create a new customer and two new books
        Customer customer = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
        Book book1 = new Book(123,1, "TEST", "Hamza Zafar", "Carleton", 10,1.99);
        Book book2 = new Book(128,1, ":D", "Hamza Zafar", "Carleton", 10,1.99);

        // Create a new cart and set it to the customer
        Cart cart = new Cart();
        cart.addBook(book1);

        // Assert that the cart has one item
        assertEquals(cart.getItems().size(),1);

        // Set the cart to the customer and checkout
        customer.setCart(cart);
        cart.setCustomer(customer);
        cart.checkout();

        // Assert that the cart is empty after checkout
        assertEquals(cart.getItems().size(),0);
    }
}