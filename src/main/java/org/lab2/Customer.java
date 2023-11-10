package org.lab2;

import java.util.ArrayList;

public class Customer extends User{

    ArrayList<Book> purchaseHistory = new ArrayList<>();

    private Cart cart;

    public Customer(String email, String phoneNumber, String username, String password, int id, String name, String address) {
        super(email, phoneNumber, username, password, id, name, address);
        this.cart = new Cart();
    }

    public void addToPurchaseHistory(Book book){
        purchaseHistory.add(book);
    }

    public void addBookToCart(Book book, int quantity){
        cart.addBook(book, quantity);
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
     * @param cart
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public Cart getCart() {
        return cart;
    }
}

