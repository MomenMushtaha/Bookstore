package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, BookStoreManagementRepository bookStoreRepository) {
        return (args) -> {
            // save a few Books
            bookRepository.save(new Book(123,"testbook1","kyler","group22",3,30.00));
            bookRepository.save(new Book(1234,"testbook2","kyler","group22",3,30.00));
            bookRepository.save(new Book(1235,"testbook3","kyler","group22",3,30.00));


            // fetch all Boooks
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
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");


            //Save a BookStoreManagement that contains a Book
            BookStoreManagement bookStoreTest = new BookStoreManagement();
            bookStoreTest.addBook(bookRepository.findById(1));
            bookStoreRepository.save(bookStoreTest);


            // fetch an individual AddressBook by ID
            BookStoreManagement bookStore = bookStoreRepository.findById(1);
            log.info("BookStoreManagement found with findById(1):");
            log.info("--------------------------------");
            log.info(bookStore.getBookList().toString());
            log.info("");
        };
    }

}
