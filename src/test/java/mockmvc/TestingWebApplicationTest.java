package mockmvc;

// Import necessary libraries
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import model.AccessingDataJpaApplication;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class is used to test the web application using MockMvc.
 * It tests the application by checking if the titles of the templates are present while running.
 */
@SpringBootTest(classes = AccessingDataJpaApplication.class)
@AutoConfigureMockMvc
public class TestingWebApplicationTest {

    // MockMvc object to perform HTTP requests
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test the home page, should have "Welcome to G22 Book Store"
     * @throws Exception if the test fails
     */
    @Test
    public void shouldReturnWebpageTitle() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to G22 Book Store")));
    }

    /**
     * Test the Owner Login page, should have "Owner Login"
     * @throws Exception if the test fails
     */
    @Test
    public void shouldReturnOwnerLoginTitle() throws Exception {
        this.mockMvc.perform(get("/owner_login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Owner Login")));
    }

    /**
     * Test the Owner Signup page, should have "Owner Signup"
     * @throws Exception if the test fails
     */
    @Test
    public void shouldReturnOwnerSignupTitle() throws Exception {
        this.mockMvc.perform(get("/owner_signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Owner Signup")));
    }

    /**
     * Test the Customer Signup page, should have "Customer Signup"
     * @throws Exception if the test fails
     */
    @Test
    public void shouldReturnCustomerSignupTitle() throws Exception {
        this.mockMvc.perform(get("/customer_signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Customer Signup")));
    }

    /**
     * Test the Customer Login page, should have "Customer Login"
     * @throws Exception if the test fails
     */
    @Test
    public void shouldReturnCustomerLoginTitle() throws Exception {
        this.mockMvc.perform(get("/customer_login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Customer Login")));
    }
}