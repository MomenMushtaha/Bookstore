package org.lab2;

/**
 * Instances of PaymentProcessor are responsible for processing transactions of items in shopping cart.
 *
 * @author: Mahtab Ameli
 */
public class PaymentProcessor {

    private Cart cart;

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
        double cartTotal = cart.calculateTotal();
        String line = "*******************************************************\n";
        System.out.println(line + "Processing payment of $" + cartTotal + "...\n\n" + cart);

        // todo authorize payment method

        boolean paymentApproved = authorizePaymentMethod();

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
     */
    private boolean updateInventory() {
        // remove items that were successfully paid for form cart
        return false;
    }

    //todo ask group if should do different payment methods for this milestone
    private boolean authorizePaymentMethod() {
        return false;
    }
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
