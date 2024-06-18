package entity;

import jakarta.persistence.*;
import model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer Entity Class.
 * The Customer extends User and has exclusive fields: purchaseHistory and cart.
 * The Customer has a OneToOne relationship with the Cart and OneToMany with Books.
 * The Customer has several methods to add Books into their cart and their purchaseHistory.
 */
@Entity
public class Customer extends User implements Serializable {

    // OneToMany relationship with Book
    @OneToMany
    private List<Book> purchaseHistory;

    // OneToOne relationship with Cart
    @OneToOne
    private Cart cart;

    // Primary key for the Customer entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Default constructor
    public Customer(){
        super();
    };

    /**
     * Overloaded constructor for the Customer class.
     * @param email Customer's email.
     * @param phoneNumber Customer's phone number.
     * @param username Customer's username.
     * @param password Customer's password.
     * @param name Customer's name.
     * @param address Customer's address.
     */
    public Customer(String email, String phoneNumber, String username, String password, String name, String address) {
        super(email, phoneNumber, username, password, name, address);
        this.purchaseHistory = new ArrayList<>();
    }

    /**
     * Adds a book to the customer's purchase history.
     * @param book The book to be added.
     */
    public void addToPurchaseHistory(Book book){
        purchaseHistory.add(book);
    }

    /**
     * Prints out the customer's purchase history.
     */
    public void printOutPurchaseHistory(){
        int val = 0;
        if(purchaseHistory.isEmpty()){
            System.out.println("The purchase history is empty!");
            return;
        }
        while(purchaseHistory.size() > val){
            System.out.println(purchaseHistory.get(val).getBookName());
            val++;
        }
    }

    /**
     * Retrieves the customer's cart.
     * @return The customer's cart.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the customer's cart.
     * @param cart The new cart.
     */
    public void setCart(Cart cart){
        this.cart = cart;
    }

    /**
     * Sets the customer's ID.
     * @param id The new ID.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the customer's ID.
     * @return The customer's ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the customer's purchase history.
     * @param history The new purchase history.
     */
    public void setPurchaseHistory(List<Book> history){
        this.purchaseHistory = history;
    }

    /**
     * Retrieves the customer's purchase history.
     * @return The customer's purchase history.
     */
    public List<Book> getPurchaseHistory(){
        return purchaseHistory;
    }
}