package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import jakarta.persistence.*;

@Entity
public class BookStoreManagement implements Serializable{

    @Id
    private long id;

    private Collection<Book> bookList;

    public BookStoreManagement() {
        bookList = new HashSet();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {return this.id;}

    public void setId(long id) {this.id = id;}

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Collection<Book> getBookList() {return bookList;}

    public void setBookList(Collection<Book> BookList) {this.bookList = BookList;}

    public void createBook(int isbn, String bookName, String author, String publisher, int quantity, float price) {
        Book book = new Book(isbn, bookName, author, publisher, quantity,price);
        this.addBook(book);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    //
    public void updateQuantity(long id, int amountToAdd) {
        if (amountToAdd <= 0) {
            System.out.println("Please enter a number greater than 0");
        }
        //algorithm to search for book in the list by isbn
        else {
            Book bookToUpdate = null;
            for (Book book : bookList) {
                if (book.getId() == id) {
                    bookToUpdate = book;
                    break; // Stop searching once the book is found
                }
            }if(bookToUpdate != null){
                bookToUpdate.addQuantity(amountToAdd);
                System.out.println("Quantity updated successfully.");
            }
            else{System.out.println("Book with ISBN " + id + " not found in inventory.");}
        }
    }

    public void removeBook(long id){
        Book bookToDelete = null;
            for (Book book : bookList) {
                if (book.getId()== id) {
                    bookToDelete = book;
                    break; // Stop searching once the book is found
                }
            }if(bookToDelete != null){
                getBookList().remove(bookToDelete);
                System.out.println("book deleted successfully.");
            }
            else{System.out.println("Book with ISBN " + id + " not found in inventory.");}
    }

    @Override
    public String toString(){
        return String.format(
                "BookStoreManagement[id=%d, books='%s']",
                id, bookList);
    }

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
