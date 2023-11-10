package org.lab2;

import java.util.ArrayList;


public class BookStoreManagement {
    private ArrayList <Book> bookList;


    public BookStoreManagement() {
        bookList = new ArrayList<>();
    }

    public void createBook(String isbn, String bookName, String author, String publisher, int quantity, float price) {
        Book book = new Book(isbn, bookName, author, publisher, quantity,price);
        this.addBook(book);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    //
    public void updateQuantity(String isbn, int amountToAdd) {
        if (amountToAdd <= 0) {
            System.out.println("Please enter a number greater than 0");
        }
        //algorithm to search for book in the list by isbn
        else {
            Book bookToUpdate = null;
            for (Book book : bookList) {
                if (book.getIsbn().equals(isbn)) {
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

    public void removeBook(String isbn){
        Book bookToDelete = null;
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn)) {
                bookToDelete = book;
                break; // Stop searching once the book is found
            }
        }if(bookToDelete != null){
            getBookList().remove(bookToDelete);
            System.out.println("book deleted successfully.");
        }
        else{System.out.println("Book with ISBN " + isbn + " not found in inventory.");}
    }


    public ArrayList<Book> getBookList() {
        return bookList;
    }

}