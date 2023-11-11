package model;

import java.util.ArrayList;

public class Customer extends User{

    ArrayList<Book> purchaseHistory = new ArrayList<>();

    private Cart cart;



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






}

