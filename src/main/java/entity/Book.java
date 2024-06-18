package entity;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Represents a book entity in the bookstore.
 * A Book entity contains details such as ISBN, name, author, publisher, quantity, price, and more.
 * This class provides methods to get book details, add or remove quantity, and handle cart operations.
 */
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int isbn;
    private int version;
    private String bookName;
    private String author;
    private String publisher;
    private int quantity;
    private double price;
    private boolean recommended; // Indicates if the book is recommended
    private int cartQuantity; // Quantity of this book in the cart
    private long cartId; // Identifier for the cart this book is in

    /**
     * Default constructor for JPA.
     */
    public Book() {
    }

    /**
     * Constructs a new Book with the specified details.
     * @param isbn The ISBN of the book.
     * @param version The version of the book.
     * @param bookName The name of the book.
     * @param author The author of the book.
     * @param publisher The publisher of the book.
     * @param quantity The quantity of the book in stock.
     * @param price The price of the book.
     */
    public Book(int isbn, int version, String bookName, String author, String publisher, int quantity, double price) {
        this.isbn = isbn;
        this.version = version;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
        this.cartQuantity = 0; // Default quantity in cart is 0
        this.recommended = false;
    }

    /**
     * Represents the composite key for the Book entity.
     */
    public static class BookId implements Serializable {
        protected String isbn;
        protected int version;

        public BookId(String isbn, int version) {
            this.isbn = isbn;
            this.version = version;
        }

        public BookId() {
        }

        public String getIsbn() {
            return isbn;
        }

        public int getVersion() {
            return version;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return isbn + ":" + version;
        }
    }

    public int getIsbn() {
        return isbn;
    }

    public int getVersion() {
        return version;
    }

    public String getBookName() {
        return bookName;
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

    public double getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int quantity) {
        this.cartQuantity = quantity;
    }

    public boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    /**
     * Adds a specified quantity to the book's stock.
     * @param amount The amount to add.
     */
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Reduces the book's stock by one.
     */
    public void reduceQuantity() {
        this.quantity -= 1;
    }

    /**
     * Adds a specified quantity to the cart.
     * @param quantity The quantity to add to the cart.
     */
    public void addToCart(int quantity) {
        // Check if sufficient stock is available
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        this.cartQuantity += quantity;
    }

    /**
     * Updates the book's stock quantity.
     * @param quantity The quantity to update.
     */
    public void updateQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, isbn='%s', bookName='%s', version=%s, author='%s', publisher='%s', quantity='%s', price='%s', cartQuantity='%s']",
                id, isbn, bookName, version, author, publisher, quantity, price, cartQuantity);
    }
}
