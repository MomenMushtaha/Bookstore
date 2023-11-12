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
        };
    }

}
