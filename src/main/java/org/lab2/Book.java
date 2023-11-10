package org.lab2;

public class Book {

    private int isbn;
    private String bookName;
    private String author;
    private String publisher;
    private int quantity;
    private double price;


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
}
