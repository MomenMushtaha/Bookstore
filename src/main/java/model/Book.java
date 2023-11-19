package model;

import jakarta.persistence.*;

import java.io.Serializable;

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

    //empty constructor for JPA
    public Book() {

    }
    public Book(int isbn,int version, String bookname, String author, String publisher, int quantity, double price) {
        this.isbn = isbn;
        this.version = version;
        this.bookname = bookname;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
        this.recommended = false;
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
    @Override
    public String toString(){
        return String.format(
                "Book[id=%d, isbn='%s' bookname='%s', version=%s, bookAuthor='%s', bookPublisher='%s', bookQuantity='%s', bookPrice='%s']",
                id, isbn, bookname,version, author, publisher, quantity, price);
    }

}
