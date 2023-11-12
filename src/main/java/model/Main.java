package model;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {



    public static void main(String[] args) {

        System.out.println("START");
        Owner owner = new Owner("owner@email.com", "123456789", "owner123", "ownerpass", "Owner Name", "Owner Address");

        // Add books to the bookstore
        Book book1 = new Book(1001, "Book1", "Author1", "Publisher1", 10, 20.99);
        Book book2 = new Book(1002, "Book2", "Author2", "Publisher2", 15, 15.99);

        owner.addBookToStore(book1);
        owner.addBookToStore(book2);

        // Display the bookstore inventory
        System.out.println("Bookstore Inventory:");
        for (Book book : owner.getBookStore()) {
            System.out.println("ISBN: " + book.getIsbn() + ", Name: " + book.getBookName() +
                    ", Quantity: " + book.getQuantity() + ", Price: $" + book.getPrice());
        }

        // Create a customer
        Customer customer = new Customer("customer@email.com", "987654321", "customer123", "customerpass", "Customer Name", "Customer Address");

        // Customer adds books to the cart
        customer.getCart().addBook(book1);
        customer.getCart().addBook(book2);

        // Display the cart contents before checkout
        System.out.println("\nCart Contents before Checkout:");
        customer.getCart().printCartContents();

        // Display the customer's purchase history before checkout
        System.out.println("\nCustomer's purchase history before Checkout:");
        customer.printOutPurchaseHistory();

        // Customer checks out
        System.out.println("\nChecking out...\n");
        customer.getCart().checkout();


        // Display the cart contents after checkout
        System.out.println("\nCart Contents after Checkout:");
        customer.getCart().printCartContents();

        // Display the customer's purchase history after checkout
        System.out.println("\nCustomer's purchase history after Checkout:");
        customer.printOutPurchaseHistory();

        // Display the bookstore inventory after checkout
        System.out.println("\nBookstore Inventory after Checkout:");
        for (Book book : owner.getBookStore()) {
            System.out.println("ISBN: " + book.getIsbn() + ", Name: " + book.getBookName() +
                    ", Quantity: " + book.getQuantity() + ", Price: $" + book.getPrice());
        }
        System.out.println("finish");
    }
}