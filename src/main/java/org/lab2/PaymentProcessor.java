package org.lab2;

/**
 * Instances of PaymentProcessor are responsible for processing transactions of items in shopping cart.
 * @author: Mahtab Ameli
 */
public class PaymentProcessor {

    /**
     * The shopping cart for which payment is being processed.
     */
    private Cart cart;

    /**
     * Unique 5-digit transaction number for each payment processing attempt.
     * todo make test for transaction number correctly incrementing
     */
    private static int transactionCounter = 10000;



    /**
     * Constructor for the class.
     * @param cart to be checked out.
     */
    public PaymentProcessor(Cart cart) {
        this.cart = cart;
    }



    //todo add transaction number to each payment processing attempt
    public boolean processPayment() {

        if (cart.isEmpty()) {
            System.out.println("\nPAYMENT CANNOT BE PROCESSED: SHOPPING CART IS EMPTY!\n");
            return false;
        }

        //todo check if cart items are available in inventory before attempting payment processing
        // if an item is not available in inventory, remove it from the cart and prompt user

        // process payment if items are available in inventory
        String transactionNumber = generateTransactionNumber(); 
        double cartTotal = cart.calculateTotal();
        String line = "*******************************************************\n";
        System.out.println(line + "Transaction #: " + transactionNumber + "\n");
        System.out.println("Processing payment of $" + cartTotal + "...\n\n" + cart);

        // todo authorize payment method

        boolean paymentApproved = authorizePaymentMethod();
        transactionCounter++;

        // if payment method approved
        if (paymentApproved) {
            System.out.println("***** PAYMENT APPROVED *****\n" + line);
            boolean inventoryUpdated =  updateInventory();
            return true;
        }

        // if payment method not approved
        System.out.println("***** PAYMENT DENIED *****\n" + line);
        return false;
    }



    //todo ask group if inventory check should be done from here
    private boolean checkInventory() {
        return false;
    }



    /**
     * Updates inventory by removing items for which payment was successful.
     * // todo ask group about inventory implementation
     */
    private boolean updateInventory() {
        // remove items that were successfully paid for form cart
        return false;
    }



    /**
     * Returns true if payment method is successful.
     * todo ask group if should do different payment methods for this milestone
     * todo ask group about user's payment method
     */
    private boolean authorizePaymentMethod() {
        return false;
    }



    /**
     * Generates a unique 5-digit transaction number returned as a string.
     */
    private String generateTransactionNumber() {
        return String.format("%05d", transactionCounter);
    }


    /**
     * for temporary "testing".
     * @param args
     */
    public static void main(String[] args) {
        /**
         * case 1: cart is empty.
         */
        Cart cart_empty = new Cart();
        PaymentProcessor p = new PaymentProcessor(cart_empty);
        p.processPayment();

        /**
         * case 2: cart has 1 item.
         */
        Cart cart_1 = new Cart();
        cart_1.addBook(new Book("ISBN_1", "BOOK NAME 1", "AUTHOR NAME 1", "PUBLISHER NAME 1", 1, 30), 1);
        PaymentProcessor p2 = new PaymentProcessor(cart_1);
        p2.processPayment();

        /**
         * case 2: cart has 2 items.
         */
        Cart cart_2 = new Cart();
        cart_2.addBook(new Book("ISBN_2", "BOOK NAME 2", "AUTHOR NAME 2", "PUBLISHER NAME 2", 2, 50), 2);
        PaymentProcessor p3 = new PaymentProcessor(cart_2);
        p3.processPayment();
    }
}
