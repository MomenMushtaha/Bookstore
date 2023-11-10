package org.lab2;

import java.util.Map;

/**
 * Instances of PaymentProcessor are responsible for processing transactions of items in shopping cart.
 *
 * @author: Mahtab Ameli
 */
public class PaymentProcessor {

    /**
     * Counter for generating unique transaction numbers.
     */
    private static int transactionCounter = 10000;

    /**
     * Processes the payment for the given shopping cart.
     *
     * @param cart     The shopping cart to process.
     * @param customer
     */
    public static void processPayment(Customer customer, Cart cart) {
        // Check if the cart is null or empty
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("PAYMENT CANNOT BE PROCESSED: SHOPPING CART IS EMPTY!");
            return;
        }

        // Generate a unique transaction number
        int transactionNumber = generateTransactionNumber();
        // Calculate the total amount for the items in the cart
        double totalAmount = cart.calculateTotal();

        System.out.println("*******************************************************");
        System.out.println("Transaction #: " + transactionNumber);
        System.out.println("\nProcessing payment of $" + totalAmount + "...\n");

        // Display the contents of the cart
        printCartContents(cart);

        // Check if the payment is approved
        if (isPaymentApproved()) {
            System.out.println("***** PAYMENT APPROVED *****");
            // Update the inventory after a successful payment
            // updateInventory(cart); //todo revisit later. For now not being done from PaymentProcessor
            updateCustomerPurchaseHistory(customer, cart);
            cart.clearCart();
        } else {
            System.out.println("***** PAYMENT DECLINED *****");
        }

        System.out.println("*******************************************************");
    }

    private static void updateCustomerPurchaseHistory(Customer customer, Cart cart) {
        for (Map.Entry<Book, Integer> entry : cart.getItems().entrySet()) {
            Book book = entry.getKey();
            // Update the quantity of each book in the inventory
            customer.addToPurchaseHistory(book);
        }
    }

    /**
     * Generates a unique transaction number.
     *
     * @return The generated transaction number.
     */
    public static int generateTransactionNumber() {
        return transactionCounter++;
    }

    /**
     * Displays the contents of the shopping cart.
     *
     * @param cart The shopping cart to display.
     */
    private static void printCartContents(Cart cart) {
        System.out.println(cart.toString());
    }

    /**
     * Simulates payment approval logic.
     * All payment is approved for milestone 1.
     *
     * @return true if the payment is approved, false otherwise.
     */
    private static boolean isPaymentApproved() {
        // Simulate payment approval logic in future milestones
        return true;
    }

    /**
     * Updates the inventory after a successful payment.
     *
     * @param cart The shopping cart containing items to update the inventory.
     */
/*    private static void updateInventory(Cart cart) {
        BookStoreManagement bookStoreManagement = new BookStoreManagement();
        for (Map.Entry<Book, Integer> entry : cart.getItems().entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            // Update the quantity of each book in the inventory
            bookStoreManagement.updateQuantity(book.getIsbn(), -quantity);
        }
    }*/
}
