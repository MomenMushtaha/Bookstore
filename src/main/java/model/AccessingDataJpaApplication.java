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
            // save a few Books
            bookRepository.save(new Book(123,"testbook1","kyler","group22",3,30.00));
            bookRepository.save(new Book(1234,"testbook2","kyler","group22",3,30.00));
            bookRepository.save(new Book(1235,"testbook3","kyler","group22",3,30.00));


            // fetch all Books
            log.info("Books found with findAll():");
            log.info("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }
            log.info("");

            // fetch an individual BuddyInfo by ID
            Book book1 = bookRepository.findById(1);
            log.info("Book found with findById(1):");
            log.info("--------------------------------");
            log.info(book1.toString());
            log.info("");

            // fetch Books by bookName
            log.info("Books found with findByBookName('testbook1'):");
            log.info("--------------------------------------------");
            bookRepository.findByBookName("testbook1").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");

            //Save a BookStoreManagement that contains a Book
            BookStoreManagement bookStoreTest = new BookStoreManagement();
            bookStoreTest.addBook(bookRepository.findById(1));
            log.info("LINE 55");
            log.info(bookStoreTest.toString());
            bookStoreRepository.save(bookStoreTest);
            log.info("LINE 57");

            log.info("LINE 56");
            // fetch an individual AddressBook by ID
            BookStoreManagement bookStore2 = bookStoreRepository.findById(1);
            log.info("BookStoreManagement found with findById(1):");
            log.info("--------------------------------");
            log.info(bookStore2.getBookList().toString());
            log.info("");

            // Save owner and make bookStoreTest his store
            Owner owner1 = new Owner("owneremail", "12345", "Owner", "ImTheBoss",1, "Boss", "bossstreet");
            owner1.setOwnersStore(bookStoreTest);
            ownerRepository.save(owner1);

            //Fetch owner by id
            Owner ownerTest = ownerRepository.findById(1);
            log.info("Owner found with findById(1):");
            log.info("--------------------------------");
            log.info(ownerTest.getName());
            log.info("");

            Cart cart1 = new Cart();
            cartRepository.save(cart1);
            cart1.setItems(null);

            //Save customer
            Customer customer1 = new Customer("teste@mail", "12345", "testMan", "password", 3, "Man", "testAddress");
            customer1.setPurchaseHistory(null);
            customer1.setCart(cart1);
            cart1.setCustomer(customer1);

            customerRepository.save(customer1);
            cartRepository.save(cart1);


            //Fetch customer by id
            Customer customerTest = customerRepository.findById(1);
            log.info("Customer found with findById(1):");
            log.info("--------------------------------");
            log.info("Customer Name: " + customerTest.getName());
            log.info("");


            //Fetch cart by id
            Cart cartTest = cartRepository.findById(1);
            log.info("Cart found with findById(1):");
            log.info("--------------------------------");
            log.info("Customer Name: " + cartTest.getCustomer().getName());
            log.info("");

            //Fetch cart by customer
            Cart cartTest2 = cartRepository.findByCustomer(customer1);
            log.info("Cart found with findByCustomer(customer1):");
            log.info("--------------------------------");
            log.info("Customer Name: " + cartTest2.getCustomer().getName());
            log.info("");




        };
    }

}
