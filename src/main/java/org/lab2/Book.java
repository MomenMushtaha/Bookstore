package org.lab2;

public class Book {

    private String isbn;
    private String bookName;
    private String author;
    private String publisher;
    private int quantity;


    public Book(String isbn, String bookName, String author, String publisher, int quantity) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public String getIsbn() {
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

    public void addQuantity(int amount){
        this.quantity = quantity + amount;
    }
}