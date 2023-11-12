package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Cart implements Serializable {
    // The cart uses a map to keep track of the books and their quantities.
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> items;

    @OneToOne
    private Customer customer;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Cart(){};
    public Cart(Customer customer) {
        //this.items = new ArrayList<>();
        this.customer = customer;
    }

    // Adds a book to the cart or increments the quantity if it already exists.
    public void addBook(Book book) {
        if(book.getQuantity()<=0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        else{
            book.reduceQuantity();
            items.add(book);}
    }

    // Removes a certain quantity of the specified book from the cart.
    public void removeBook(Book book) {
        book.addQuantity(1);
        items.remove(book);
    }

    // Empties the cart.
    public void clearCart() {
        for(Book book : items){
        book.addQuantity(1);
    }
        items.clear();
    }

    // Prints out the contents of the cart.
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

    // Calculates the total price for the items in the cart.
    public double calculateTotal() {
        double total = 0.0;
        for (Book book: items) {
            total += book.getPrice();
        }
        return total;
    }

    public void checkout(){
        PaymentProcessor.processPayment(getCustomer(), this);
        items.clear();
    }


    /**
     * Returns items hashmap as a String.
     *
     * @author: Mahtab Ameli
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // Retrieves the cart's contents.
    public List<Book> getItems() {
        return items;
    }
    public void setItems(List<Book> items){
        this.items = items;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
