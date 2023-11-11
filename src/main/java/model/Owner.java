package model;

import java.util.ArrayList;
import java.util.Collection;

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

    public void updateStoreQuantity(int isbn, int amount){
        ownersStore.updateQuantity(isbn, amount);
    }

    public Collection<Book> getBookStore(){
        return ownersStore.getBookList();
    }

    public BookStoreManagement getOwnersStore() {
        return ownersStore;
    }

}
