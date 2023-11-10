package org.lab2;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    // The cart uses a map to keep track of the books and their quantities.
    private Map<Book, Integer> items;
    private Customer customer;

    public Cart() {
        items = new HashMap<>();
    }

    // Adds a book to the cart or increments the quantity if it already exists.
    public void addBook(Book book, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        items.merge(book, quantity, Integer::sum);
    }

    // Removes a certain quantity of the specified book from the cart.
    public void removeBook(Book book, int quantity) {
        if (!items.containsKey(book)) {
            System.out.println("Book not found in the cart.");
            return;
        }
        if (quantity <= 0 || quantity > items.get(book)) {
            throw new IllegalArgumentException("Invalid quantity.");
        }
        int currentQuantity = items.get(book);

        if (quantity >= currentQuantity) {
            // If the quantity to remove is equal to or greater than the current quantity, remove the book from the cart.
            items.remove(book);
        } else {
            items.put(book, currentQuantity - quantity);
        }
    }

    // Retrieves the cart's contents.
    public Map<Book, Integer> getItems() {
        return items;
    }

    // Empties the cart.
    public void clearCart() {
        items.clear();
    }

    // Prints out the contents of the cart.
    public void printCartContents() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Cart Contents:");
            for (Map.Entry<Book, Integer> entry : items.entrySet()) {
                Book book = entry.getKey();
                Integer quantity = entry.getValue();
                System.out.println("Book: " + book.getBookName() + ", Quantity: " + quantity);
            }
        }
    }

    // Calculates the total price for the items in the cart.
    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Book, Integer> entry : items.entrySet()) {
            Book book = entry.getKey();
            Integer quantity = entry.getValue();
            total += book.getPrice() * quantity;
        }
        return total;
    }

    public void checkout(){
        PaymentProcessor.processPayment(this);
    }


    /**
     * Returns items hashmap as a String.
     *
     * @author: Mahtab Ameli
     */
    @Override
    public String toString() {
        StringBuilder cartString = new StringBuilder("Items:\n");
        if (items.isEmpty()) {
            cartString.append("The cart is empty.\n");
        } else {
            for (Map.Entry<Book, Integer> entry : items.entrySet()) {
                Book book = entry.getKey();
                Integer quantity = entry.getValue();
                cartString.append("Book: ")
                        .append(book.getBookName())
                        .append(", Quantity: ")
                        .append(quantity)
                        .append("\n");
            }
        }
        return cartString.toString();
    }
}
