package model;

// Import necessary libraries and packages
import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.*;

/**
 * AccessingDataJpaApplication is the main class that gets run to generate repositories and populate them with test objects.
 * Running this class is mandatory for testing the program as a whole: Model, View, Controller.
 * Keep the application running while testing functions of the bookstore website.
 *
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * @Configuration: Tags the class as a source of bean definitions for the application context.
 * @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'model', 'controller', and 'repository' packages, allowing it to find the controllers.
 * @EntityScan: Configures the base packages to scan for JPA entities. Presumably, you would only need to use this if you are using JPA and your entities are in a package not visible to Spring Boot.
 * @EnableJpaRepositories: Enables JPA repositories. This will scan the package of the annotated configuration class for Spring Data repositories by default.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"model", "controller", "repository"})
@EntityScan("entity")
@EnableJpaRepositories("repository")
public class AccessingDataJpaApplication {
    // Define a logger to output useful debugging information
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    /**
     * The main method that serves as the entry point for the application.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Run the application using the specified sources (AccessingDataJpaApplication.class in this case).
        // This method delegates to the SpringApplication class, which will set up default configuration and start Spring ApplicationContext.
        SpringApplication.run(AccessingDataJpaApplication.class);
    }
}
