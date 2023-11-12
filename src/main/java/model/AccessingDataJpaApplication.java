package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner group22Demo(BookRepository bookRepository,
                                         BookStoreManagementRepository bookStoreRepository,
                                         CartRepository cartRepository,
                                         OwnerRepository ownerRepository,
                                         CustomerRepository customerRepository) {
        return (args) -> {
// Create a new Owner with initial details
            Owner owner1 = new Owner("owneremail", "12345", "Owner", "ImTheBoss", "Boss", "bossstreet");
            System.out.println("Owner made");

// Retrieve the BookStoreManagement associated with the owner
            BookStoreManagement owner1BookStore = owner1.getOwnersStore();
// Save the BookStoreManagement to the database
            bookStoreRepository.save(owner1BookStore);
            System.out.println("bookstore saved");

// Save the owner to the database
            ownerRepository.save(owner1);
            System.out.println("owner saved");

// Create and save two books to the database
            Book book1 = new Book(123, "TEST", "Hamza Zafar", "Carleton", 10, 1.99);
            bookRepository.save(book1);
            Book book2 = new Book(128, ":D", "Hamza Zafar", "Carleton", 10, 1.99);
            bookRepository.save(book2);

// Associate the first book with the owner's BookStoreManagement and save changes
            owner1.getOwnersStore().addBook(book1);
            bookStoreRepository.save(owner1BookStore);
            ownerRepository.save(owner1);

// Associate the second book with the owner's BookStoreManagement
            owner1.getOwnersStore().addBook(book2);
            System.out.println(owner1.getOwnersStore().getBookList());

// Create a new Cart and save it to the database
            Cart cart1 = new Cart();
            cartRepository.save(cart1);

// Create a new Customer and associate it with the Cart, then save to the database
            Customer customer1 = new Customer("teste@mail", "12345", "testMan", "password", "Man", "testAddress");
            customer1.setPurchaseHistory(null);
            customer1.setCart(cart1);
            cart1.setCustomer(customer1);
            customerRepository.save(customer1);
            cartRepository.save(cart1);

// Fetch a customer by ID
            Customer customerTest = customerRepository.findById(1);
            log.info("Customer found with findById(1):");
            log.info("--------------------------------");
            log.info("Customer Name: " + customerTest.getName());
            log.info("");

// Fetch a cart by ID
            Cart cartTest = cartRepository.findById(1L);
            log.info("Cart found with findById(1):");
            log.info("--------------------------------");
            log.info("Customer Name: " + cartTest.getCustomer().getName());
            log.info("");

// Fetch a cart by customer
            Cart cartTest2 = cartRepository.findByCustomer(customer1);
            log.info("Cart found with findByCustomer(customer1):");
            log.info("--------------------------------");
            log.info("Customer Name: " + cartTest2.getCustomer().getName());
            log.info("");


        };
    }

}
