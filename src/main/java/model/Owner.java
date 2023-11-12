package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Owner extends User implements Serializable {

    @OneToOne
    BookStoreManagement ownersStore;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Owner(){};
    public Owner(String email, String phoneNumber, String username, String password, String name, String address) {
        super(email, phoneNumber, username, password, name, address);
        this.setOwnersStore(new BookStoreManagement());
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
    public void setOwnersStore(BookStoreManagement store){
        this.ownersStore = store;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}
