package entity;

import jakarta.persistence.*;

import java.io.Serializable;

//Book Entity Class
//A Book is an object that has several fields to describe one such as its isbn, name, author, ect.
//This class has getters for all the Books fields, providing a means for other Entities such as Owner
//to adding or removing specific books from their bookStore based on their isbn for example.
@Entity
public class Book implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int isbn;
    private int version;
    public String bookname;
    private String author;
    private String publisher;
    private int quantity;
    private double price;
    private boolean recommended;
    private int cartQuantity; // Quantity of this book in the cart
    private long cartId; // Identifier for the cart this book is in

    //empty constructor for JPA
    public Book() {

    }
    public Book(int isbn, int version, String bookname, String author, String publisher, int quantity, double price) {
        this.isbn = isbn;
        this.version = version;
        this.bookname = bookname;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
        this.recommended = false;
        this.cartQuantity = 0; // Default quantity in cart is 0
    }

    public int getIsbn() {
        return isbn;
    }
    public int getVersion() {
        return version;
    }

    public String getBookName() {
        return bookname;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {return price;}

    public void addQuantity(int amount){
        this.quantity = quantity + amount;
    }

    public void reduceQuantity(){
        this.quantity = quantity - 1;
    }

    public long getId() {
        return id;
    }
    public boolean getRecommended() {return recommended;}
    public void setRecommended(boolean recommended) {this.recommended = recommended;}

    // Cart-related methods
    public void addToCart(int quantity) {
        // Check if sufficient stock is available
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        this.quantity -= quantity;
        this.cartQuantity += quantity;
    }

    public void removeFromCart(int quantity) {
        this.quantity += quantity;
        this.cartQuantity -= quantity;
        if (this.cartQuantity < 0) {
            this.cartQuantity = 0;
        }
    }
    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, isbn='%s', bookname='%s', version=%s, author='%s', publisher='%s', quantity='%s', price='%s', cartQuantity='%s']",
                id, isbn, bookname, version, author, publisher, quantity, price, cartQuantity);
    }
}
