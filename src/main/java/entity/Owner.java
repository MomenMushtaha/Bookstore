package entity;

import jakarta.persistence.*;
import model.User;
import java.io.Serializable;
import java.util.Collection;

/**
 * Owner Entity Class.
 * The Owner extends User and has exclusive BookStoreManagement.
 * The Owner has a OneToOne relationship with the BookStoreManagement.
 * The Owner has several methods to manage their respective bookstore such as adding or removing books.
 */
@Entity
public class Owner extends User implements Serializable {

    // OneToOne relationship with BookStoreManagement
    @OneToOne
    private BookStoreManagement ownersStore;

    // Primary key for the Owner entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Default constructor
    public Owner(){};

    /**
     * Overloaded constructor for the Owner class.
     * @param email Owner's email.
     * @param phoneNumber Owner's phone number.
     * @param username Owner's username.
     * @param password Owner's password.
     * @param name Owner's name.
     * @param address Owner's address.
     */
    public Owner(String email, String phoneNumber, String username, String password, String name, String address) {
        super(email, phoneNumber, username, password, name, address);
        this.setOwnersStore(new BookStoreManagement());
    }

    /**
     * Adds a book to the owner's store.
     * @param book The book to be added.
     */
    public void addBookToStore(Book book){
        ownersStore.addBook(book);
    }

    /**
     * Removes a book from the owner's store.
     * @param book The book to be removed.
     */
    public void removeBookFromStore(Book book){
        ownersStore.removeBook(book.getIsbn());
    }

    /**
     * Updates the quantity of a book in the owner's store.
     * @param isbn The ISBN of the book.
     * @param amount The new quantity.
     */
    public void updateStoreQuantity(int isbn, int amount){
        ownersStore.updateQuantity(isbn, amount);
    }

    /**
     * Retrieves the list of books in the owner's store.
     * @return The list of books.
     */
    public Collection<Book> getBookStore(){
        return ownersStore.getBookList();
    }

    /**
     * Retrieves the owner's store.
     * @return The owner's store.
     */
    public BookStoreManagement getOwnersStore() {
        return ownersStore;
    }

    /**
     * Sets the owner's store.
     * @param store The new store.
     */
    public void setOwnersStore(BookStoreManagement store){
        this.ownersStore = store;
    }

    /**
     * Sets the owner's ID.
     * @param id The new ID.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the owner's ID.
     * @return The owner's ID.
     */
    public long getId() {
        return id;
    }
}