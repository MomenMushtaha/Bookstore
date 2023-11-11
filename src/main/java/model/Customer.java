package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Customer extends User implements Serializable {

    ArrayList<Book> purchaseHistory = new ArrayList<>();

    @OneToOne
    private Cart cart;
    @Id
    private Integer id;


    public Customer(){
    };

    public Customer(String email, String phoneNumber, String username, String password, int id, String name, String address) {
        super(email, phoneNumber, username, password, id, name, address);
        this.cart = new Cart(this);
    }

    public void addToPurchaseHistory(Book book){
        purchaseHistory.add(book);
    }

    public void printOutPurchaseHistory(){
        int val = 0;
        if(purchaseHistory.isEmpty()){
            System.out.println("The purchase history is empty!");
            return;
        }
        while(purchaseHistory.size() > val){
            System.out.println(purchaseHistory.get(val).getBookName());
            val++;
        }
    }

    /**
     * Setter for cart.
     * @param
     */
/*    public void setCart(Customer Cart, Cart self) {
        this.cart = cart;
    }*/


    public Cart getCart() {
        return cart;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

