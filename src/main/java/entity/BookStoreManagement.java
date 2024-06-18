package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import entity.Book;
import jakarta.persistence.*;

/**
 * This class represents the management of a bookstore.
 * It contains a collection of books and provides methods for managing these books.
 * The bookstore owner can add or remove books from the bookstore.
 */
@Entity
public class BookStoreManagement implements Serializable{

    @Id
    private long id;
    private Collection<Book> bookList;

    /**
     * Default constructor that initializes the book list.
     */
    public BookStoreManagement() {
        bookList = new HashSet();
    }

    /**
     * Retrieves the ID of the bookstore.
     * @return The ID of the bookstore.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {return this.id;}

    /**
     * Sets the ID of the bookstore.
     * @param id The new ID.
     */
    public void setId(long id) {this.id = id;}

    /**
     * Retrieves the list of books in the bookstore.
     * @return The list of books.
     */
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Collection<Book> getBookList() {return bookList;}

    /**
     * Sets the list of books in the bookstore.
     * @param BookList The new list of books.
     */
    public void setBookList(Collection<Book> BookList) {this.bookList = BookList;}

    /**
     * Creates a new book and adds it to the bookstore.
     * @param isbn The ISBN of the book.
     * @param version The version of the book.
     * @param bookname The name of the book.
     * @param author The author of the book.
     * @param publisher The publisher of the book.
     * @param quantity The quantity of the book.
     * @param price The price of the book.
     */
    public void createBook(int isbn,int version, String bookname, String author, String publisher, int quantity, float price) {
        Book book = new Book(isbn,version, bookname, author, publisher, quantity,price);
        this.addBook(book);
    }

    /**
     * Adds a book to the bookstore.
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        bookList.add(book);
    }

    /**
     * Removes a book from the bookstore by its ISBN.
     * @param isbn The ISBN of the book to be removed.
     */
    public void removeBook(long isbn){
        // Algorithm to search for book in the list by ISBN
        Book bookToDelete = null;
        for (Book book : bookList) {
            if (book.getIsbn()== isbn) {
                bookToDelete = book;
                break; // Stop searching once the book is found
            }
        }
        if(bookToDelete != null){
            getBookList().remove(bookToDelete);
            System.out.println("book deleted successfully.");
        }
        else{System.out.println("Book with ISBN " + id + " not found in inventory.");}
    }

    /**
     * Updates the quantity of a book in the bookstore.
     * @param isbn The ISBN of the book.
     * @param amountToAdd The amount to add to the book's quantity.
     */
    public void updateQuantity(long isbn, int amountToAdd) {
        if (amountToAdd <= 0) {
            System.out.println("Please enter a number greater than 0");
        }
        else {
            // Algorithm to search for book in the list by ISBN
            Book bookToUpdate = null;
            for (Book book : bookList) {
                if (book.getIsbn() == isbn) {
                    bookToUpdate = book;
                    break; // Stop searching once the book is found
                }
            }
            if(bookToUpdate != null){
                bookToUpdate.addQuantity(amountToAdd);
                System.out.println("Quantity updated successfully.");
            }
            else{System.out.println("Book with ISBN " + id + " not found in inventory.");}
        }
    }

    /**
     * Returns a string representation of the bookstore management.
     * @return A string representation of the bookstore management.
     */
    @Override
    public String toString(){
        return String.format(
                "BookStoreManagement[id=%d, books='%s']",
                id, bookList);
    }

    /**
     * Prints the details of the bookstore management.
     */
    public void getBookStoreManagement(){
        if (bookList.isEmpty()){
            System.out.print("The Book Store is empty!" + "\n");
        }
        else{
            for (Book book : bookList){
                System.out.println("ISBN= " + book.getIsbn() + "Name= " + book.getBookName() + "Author = " + book.getAuthor() +
                        "Publisher = " + book.getPublisher() + "Quantity = " + book.getQuantity() + "Price = " + book.getPrice());
            }
        }
    }
}