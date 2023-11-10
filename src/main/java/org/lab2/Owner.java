package org.lab2;

import java.util.ArrayList;
public class Owner extends User{

    BookStoreManagement ownersStore = new BookStoreManagement();
    public Owner(String email, String phoneNumber, String username, String password, int id, String name, String address) {
        super(email, phoneNumber, username, password, id, name, address);
    }

    public void addBookToStore(Book book){
        ownersStore.addBook(book);
    }

    public void removeBookFromStore(Book book){
        ownersStore.removeBook(book.getIsbn());
    }

    public void updateStoreQuantity(String isbn, int amount){
        ownersStore.updateQuantity(isbn, amount);
    }

    public ArrayList<Book> getBookStore(){
        return ownersStore.getBookList();
    }

}
