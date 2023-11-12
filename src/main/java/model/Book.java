package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Book implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int isbn;
    private String bookName;
    private String author;
    private String publisher;
    private int quantity;
    private double price;

    //empty constructor for JPA
    public Book() {

    }
    public Book(int isbn, String bookName, String author, String publisher, int quantity, double price) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIsbn() {
        return isbn;
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

    @Override
    public String toString(){
        return String.format(
                "Book[id=%d, isbn='%s' bookName='%s', bookAuthor='%s', bookPublisher='%s', bookQuantity='%s', bookPrice='%s']",
                id, isbn, bookName, author, publisher, quantity, price);
    }

}
