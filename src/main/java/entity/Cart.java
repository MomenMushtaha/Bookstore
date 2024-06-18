package entity;

import jakarta.persistence.*;
import model.PaymentProcessor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart Entity Class.
 * The Cart has a OneToMany relationship with Books and a OneToOne with a Customer.
 * The Cart is used to add/remove books in it and bringing itself into checkout with what Books it currently holds.
 */
@Entity
public class Cart implements Serializable {

    // OneToMany relationship with Book
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> items;

    // OneToOne relationship with Customer
    @OneToOne
    private Customer customer;

    // Primary key for the Cart entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Default constructor for the Cart class.
     * Initializes the items list.
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Overloaded constructor for the Cart class.
     * @param customer The customer who owns the cart.
     */
    public Cart(Customer customer) {
        this.customer = customer;
    }

    /**
     * Adds a book to the cart.
     * @param book The book to be added.
     * @throws IllegalArgumentException if the book quantity is zero or less.
     */
    public void addBook(Book book) {
        if(book.getQuantity()<=0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        else{
            items.add(book);
        }
    }

    /**
     * Removes a book from the cart.
     * @param book The book to be removed.
     */
    public void removeBook(Book book) {
        book.addQuantity(1);
        items.remove(book);
    }

    /**
     * Empties the cart.
     */
    public void clearCart() {
        for(Book book : items){
            book.addQuantity(1);
        }
        items.clear();
    }

    /**
     * Prints out the contents of the cart.
     */
    public void printCartContents() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Cart Contents:");
            for (Book book : items){
                System.out.println("Book: " + book.getBookName());
            }
        }
    }

    /**
     * Calculates the total price for the items in the cart.
     * @return The total price.
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Book book: items) {
            total += book.getPrice();
        }
        return total;
    }

    /**
     * Clears the items in the cart.
     */
    public void clearItems(){
        items.clear();
    }

    /**
     * Returns items list as a String.
     * @return The string representation of the items in the cart.
     */
    @Override
    public String toString() {
        StringBuilder cartString = new StringBuilder("Items:\n");
        if (items == null || items.isEmpty()) {
            cartString.append("The cart is empty.\n");
        } else {
            for (Book book : items) {
                cartString.append("Book: ")
                        .append(book.getBookName())
                        .append("\n");
            }
        }
        return cartString.toString();
    }

    /**
     * Retrieves the customer who owns the cart.
     * @return The customer who owns the cart.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer who owns the cart.
     * @param customer The new customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Processes the payment for the items in the cart and clears the cart.
     */
    public void checkout(){
        PaymentProcessor.processPayment(getCustomer(), this);
        this.clearCart();
    }

    /**
     * Retrieves the items in the cart.
     * @return The items in the cart.
     */
    public List<Book> getItems() {
        return items;
    }

    /**
     * Sets the items in the cart.
     * @param items The new items.
     */
    public void setItems(List<Book> items){
        this.items = items;
    }

    /**
     * Sets the cart's ID.
     * @param id The new ID.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the cart's ID.
     * @return The cart's ID.
     */
    public long getId() {
        return id;
    }
}