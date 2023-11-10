package org.lab2;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {



    public static void main(String[] args) {
        // Create an owner and a customer
        Owner owner = new Owner("owneremail", "12345", "Owner", "ImTheBoss", 1, "Boss", "bossstreet");
        Customer customer = new Customer("test@mail", "12345", "testMan", "password", 3, "Man", "testAddress");

        // Owner adds books to the store
        Book book1 = new Book("ISBN123", "TEST", "Hamza Zafar", "Carleton", 10, 1.99);
        Book book2 = new Book("ISBN128", ":D", "Hamza Zafar", "Carleton", 10, 1.99);

        owner.addBookToStore(book1);
        owner.addBookToStore(book2);

        // Display initial state
        System.out.println("Initial state:");
        System.out.println("Owner's store: " + owner.getBookStore().size() + " books");
        System.out.println("Customer's purchase history: " + customer.purchaseHistory.size() + " books");

        // Display the contents of the owner's store
        System.out.println("\nOwner's Store Contents:");
        for (Book book : owner.getBookStore()) {
            System.out.println(book.getBookName() + " - Quantity: " + book.getQuantity());
        }

        // Customer adds books to the cart
        customer.addBookToCart(book1, 2);
        customer.addBookToCart(book2, 1);

        // Display the cart contents
        System.out.println("\nCart Contents:");
        customer.getCart().printCartContents();
        System.out.println("Total price: $" + customer.getCart().calculateTotal());

        // Clear the cart after the purchase is completed
        customer.getCart().clearCart();

        // Display updated state
        System.out.println("\nUpdated state:");
        System.out.println("Owner's store: " + owner.getBookStore().size() + " books");
        System.out.println("Customer's purchase history: " + customer.purchaseHistory.size() + " books");

        // Display the updated contents of the owner's store
        System.out.println("\nOwner's Store Contents (after purchase):");
        for (Book book : owner.getBookStore()) {
            System.out.println(book.getBookName() + " - Quantity: " + book.getQuantity());
        }


    }
}