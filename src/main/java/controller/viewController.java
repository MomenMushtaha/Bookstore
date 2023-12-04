package controller;

import entity.BookStoreManagement;
import entity.Cart;
import entity.Customer;
import entity.Owner;
import jakarta.servlet.http.HttpSession;
import repository.BookStoreManagementRepository;
import repository.CartRepository;
import repository.CustomerRepository;
import repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class viewController {
    // Injecting dependencies for the used repositories
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookStoreManagementRepository bookStoreRepository;


    /**
     * Method Home to handle requests to the root URL and display the home page
     * @return a direction to the next appropriate page
     */
    @GetMapping("/")
    public String Home() {return "home_portal";}


    /**
     * Method HomeInitialize to create a sample owner and bookstore management entity upon accessing the root URL
     * @return a direction to the next appropriate page
     */
    @RequestMapping("/")
    public @ResponseBody String HomeInitialize() {
        // Creation of a new owner object
        Owner owner1 = new Owner("owneremail@gmail.com", "6132113454", "Owner", "ImTheBoss", "Boss", "921bossstreet");
        System.out.println("owner user created");
        // Retrieving and saving the owner's bookstore management details
        BookStoreManagement owner1BookStore = owner1.getOwnersStore();
        // Save the BookStoreManagement to the database
        bookStoreRepository.save(owner1BookStore);
        System.out.println("bookstore saved");
        // Save the owner to the database
        ownerRepository.save(owner1);
        System.out.println("owner user saved");
        return owner1BookStore.toString();
    }


    /**
     * Method CustomerSignup to direct users to the customer signup page
     * @return a direction to the next appropriate page
     */
    @GetMapping("/customer_signup")
    public String CustomerSignup() {return "customer_signup";}


    /**
     * Method CustomerSignupControl to handle the customer signup form submission
     * @return a direction to the next appropriate page
     */
    @PostMapping("/customer_signup")
    public String CustomerSignupControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            @RequestParam(name="name", required=false, defaultValue="") String name,
            @RequestParam(name="address", required=false, defaultValue="") String address,
            @RequestParam(name="email", required=false, defaultValue="") String email,
            @RequestParam(name="phonenumber", required=false, defaultValue="") String phonenumber,
            Model model) {

        // Check if the username already exists in the database
        Optional<Customer> result = customerRepository.findByUsername(username);

        // Validation for non-empty username and password
        if (result.isEmpty()) {
            if (!username.isEmpty() && !password.isEmpty()) {
                // Creating and saving a new customer
                Cart cart = new Cart();
                Customer customer = new Customer(email, phonenumber, username, password, name, address);
                cartRepository.save(cart);
                customer.setCart(cart);
                customerRepository.save(customer);
                cart.setCustomer(customer);
                cartRepository.save(cart);
                customerRepository.save(customer);

                return "redirect:/customer_login";
            } else {
                // Handling empty username and password:
                // 1. the controller will show the error message below
                // 2. the controller will stay on the customer_sign page
                model.addAttribute("signup_error", "Username or Password input is empty. Please set something.");
                return "customer_signup";
            }
        } else {
            // Handling already used username:
            // similar protocol as above
            model.addAttribute("signup_error", "Username already used, choose a different username!");
            return "customer_signup";
        }
    }



    /**
     * Method CustomerLogin to direct users to the customer login page
     * @return a direction to the next appropriate page
     */
    @GetMapping("/customer_login")
    public String CustomerLogin() {
        return "customer_login";
    }


    /**
     * Method CustomerLoginControl to handle the customer login form submission
     * @return a direction to the next appropriate page
     */
    @PostMapping( value = "/customer_login", params = "customer_login")
    public String CustomerLoginControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
                    HttpSession session, Model model) {
        Optional<Customer> result = customerRepository.findByUsername(username);
        //Optional<Cart> cartResult = cartRepository.findByCustomer(username);

        //Add appropriate handling and redirections based on login success or failure
        if (result.isPresent()) {
            Customer customer = result.get();
            String customerPassword = customer.getPassword();
            if(customerPassword.equals(password)) {
                Long cartId = customer.getCart().getId();
                model.addAttribute("username", username);
                model.addAttribute("cartId", cartId);
                session.setAttribute("username", username);
               session.setAttribute("cartId", cartId);
               return "redirect:/bookstore_portal";
           }
        }
        model.addAttribute("login_error", "Invalid username or password");
        return "customer_login";
    }

    @GetMapping("/edit_book")
    public String EditBook(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            return "redirect:/owner_login";
        }

        model.addAttribute("owner", owner.get());
        return "edit_book";
    }

}