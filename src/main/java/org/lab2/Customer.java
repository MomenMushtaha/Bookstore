package org.lab2;

import java.util.ArrayList;

public class Customer extends User{

    ArrayList<Book> purchaseHistory = new ArrayList<>();

    private String cart;

    public Customer(String email, String phoneNumber, String username, String password, int id, String name, String address) {
        super(email, phoneNumber, username, password, id, name, address);
    }

    public void addToPurchaseHistory(Book book){
        purchaseHistory.add(book);
    }

    public void removeFromPurchaseHistory(Book book){
        if(purchaseHistory.contains(book)){
            purchaseHistory.remove(book);
        }
        else{
            System.out.println("The given book is not in the purchase history!");
        }
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
}
