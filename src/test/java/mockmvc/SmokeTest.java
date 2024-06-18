package mockmvc;

// Importing necessary libraries
import static org.assertj.core.api.Assertions.assertThat;

import controller.CustomerController;
import controller.OwnerController;
import model.AccessingDataJpaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class is a smoke test to ensure that the Spring application context is correctly creating the controllers.
 * It uses the AccessingDataJpaApplication.class to start a Spring application context.
 * Assertions are made for the controllers to prove that the context is creating them.
 */
@SpringBootTest(classes = AccessingDataJpaApplication.class)
@ComponentScan(basePackages = {"model", "controller", "repository", "entity"})
public class SmokeTest {

    // Autowiring the OwnerController
    @Autowired
    private OwnerController ownercontroller;

    // Autowiring the CustomerController
    @Autowired
    private CustomerController customerController;

    /**
     * This test ensures that the OwnerController is loaded into the Spring application context.
     * @throws Exception if the OwnerController is not loaded into the Spring application context.
     */
    @Test
    public void contextLoadsOwnerController() throws Exception {
        assertThat(ownercontroller).isNotNull();
    }

    /**
     * This test ensures that the CustomerController is loaded into the Spring application context.
     * @throws Exception if the CustomerController is not loaded into the Spring application context.
     */
    @Test
    public void contextLoadsCustomerController() throws Exception {
        assertThat(customerController).isNotNull();
    }

}