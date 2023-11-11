package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import jakarta.persistence.*;

import java.util.ArrayList;
@Entity
public class BookStoreManagement implements Serializable{

    @Id
    private Integer id = null;

    private ArrayList<Book> bookList;

    public BookStoreManagement() {
        bookList = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    public Integer getId() {return this.id;}

    public void setId(Integer id) {this.id = id;}

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public ArrayList<Book> getBookList() {return bookList;}

    public void setBookList(ArrayList<Book> BookList) {this.bookList = BookList;}

    public void createBook(int isbn, String bookName, String author, String publisher, int quantity, float price) {
        Book book = new Book(isbn, bookName, author, publisher, quantity,price);
        this.addBook(book);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    //
    public void updateQuantity(int isbn, int amountToAdd) {
        if (amountToAdd <= 0) {
            System.out.println("Please enter a number greater than 0");
        }
        //algorithm to search for book in the list by isbn
        else {
            Book bookToUpdate = null;
            for (Book book : bookList) {
                if (book.getIsbn() == isbn) {
                    bookToUpdate = book;
                    break; // Stop searching once the book is found
                }
            }if(bookToUpdate != null){
                bookToUpdate.addQuantity(amountToAdd);
                System.out.println("Quantity updated successfully.");
            }
            else{System.out.println("Book with ISBN " + isbn + " not found in inventory.");}
        }
    }

    public void removeBook(int isbn){
        Book bookToDelete = null;
            for (Book book : bookList) {
                if (book.getIsbn()== isbn) {
                    bookToDelete = book;
                    break; // Stop searching once the book is found
                }
            }if(bookToDelete != null){
                getBookList().remove(bookToDelete);
                System.out.println("book deleted successfully.");
            }
            else{System.out.println("Book with ISBN " + isbn + " not found in inventory.");}
    }

    @Override
    public String toString(){
        return String.format(
                "BookStoreManagement[id=%d, books='%s']",
                id, bookList);
    }
}
