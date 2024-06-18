package model;

import entity.Book;
import entity.Cart;
import entity.Customer;

/**
 * The PaymentProcessor class is responsible for processing transactions of items in a shopping cart.
 * It is a utility class with static methods, hence it is not meant to be instantiated.
 */
public class PaymentProcessor {

    /**
     * A static counter used for generating unique transaction numbers.
     * Initialized to 10000 and increments with each transaction.
     */
    private static int transactionCounter = 10000;

    /**
     * Processes the payment for the given shopping cart.
     * If the cart is null or empty, a message is printed and the method returns.
     * Otherwise, a unique transaction number is generated, the total amount is calculated,
     * and the payment is processed. The customer's purchase history is updated and the cart is cleared.
     *
     * @param customer The customer who is making the payment.
     * @param cart     The shopping cart containing the items to be processed.
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

        System.out.println("***** PAYMENT APPROVED *****");
        updateCustomerPurchaseHistory(customer, cart);
        cart.clearCart();

        System.out.println("*******************************************************");
    }

    /**
     * Updates the customer's purchase history with the items from the cart.
     * Iterates over each book in the cart and adds it to the customer's purchase history.
     *
     * @param customer The customer whose purchase history is to be updated.
     * @param cart     The shopping cart containing the items to be added to the purchase history.
     */
    private static void updateCustomerPurchaseHistory(Customer customer, Cart cart) {
        for (Book book : cart.getItems()) {
            // Update the quantity of each book in the inventory
            customer.addToPurchaseHistory(book);
        }
    }

    /**
     * Generates a unique transaction number by incrementing a static counter.
     *
     * @return The generated transaction number.
     */
    public static int generateTransactionNumber() {
        return transactionCounter++;
    }

    /**
     * Prints the contents of the shopping cart.
     * This method is used for displaying the items in the cart during the payment process.
     *
     * @param cart The shopping cart whose contents are to be printed.
     */
    private static void printCartContents(Cart cart) {
        System.out.println(cart.toString());
    }
}