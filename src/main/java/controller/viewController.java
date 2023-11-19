package controller;

import jakarta.servlet.http.HttpSession;
import model.*;
import model.BookStoreManagementRepository;
import model.CartRepository;
import model.CustomerRepository;
import model.OwnerRepository;
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
     * Method OwnerSignup to direct users to the owner signup page
     * @return a direction to the next appropriate page
     */
    @GetMapping("/owner_signup")
    public String OwnerSignup() {return "owner_signup";}


    /**
     * Method OwnerSignUpControl to handle the owner signup form submission
     * @return a direction to the next appropriate page
     */
    @PostMapping("/owner_signup")
    public String OwnerSignupControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            @RequestParam(name="name", required=false, defaultValue="") String name,
            @RequestParam(name="address", required=false, defaultValue="") String address,
            @RequestParam(name="email", required=false, defaultValue="") String email,
            @RequestParam(name="phonenumber", required=false, defaultValue="") String phonenumber,
            Model model) {
        // does username already exist
        Optional<Owner> result = ownerRepository.findByUsername(username);
        if (result.isEmpty()) {
            // Add appropriate handling and redirections based on signup success or failure
            if(!username.equals("") && !password.equals("")) {
                Owner owner = new Owner(email, phonenumber, username, password, name, address);
                BookStoreManagement ownerBookStore = new BookStoreManagement();
                bookStoreRepository.save(ownerBookStore);
                owner.setOwnersStore(ownerBookStore);
                ownerRepository.save(owner);
                return "redirect:/owner_login";
            } else {
                model.addAttribute("signup_error", "Username or Password input is empty. Please set something.");
                return "owner_signup";
            }
        } else {
            model.addAttribute("signup_error", "Username already used, choose a different username!");
            return "owner_signup";
        }}


    /**
     * Method OwnerLogin to direct users to the owner login page
     * @return a direction to the next appropriate page
     */
    @GetMapping("/owner_login")
    public String OwnerLogin() {
        return "owner_login";
    }


    /**
     * Method OwnerLoginControl to handle the owner login form submission
     * @return a direction to the next appropriate page
     */
    @PostMapping( value = "/owner_login", params = "owner_login")
    public String OwnerLoginControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            HttpSession session, Model model) {
        Optional<Owner> result = ownerRepository.findByUsername(username);
        if (result.isPresent()) {
            // Add appropriate handling and redirections based on login success or failure
            Owner owner = result.get();
            String ownerPassword = owner.getPassword();
            if(ownerPassword.equals(password)){
                model.addAttribute("username", username);
                session.setAttribute("username", username);
                return "redirect:/owner_portal";
            }
        }
        model.addAttribute("login_error", "Invalid username or password");
        return "owner_login";
    }


    /**
     * Method OwnerLogout to handle owner logout
     * @param session
     * @return
     */
    @GetMapping("/owner_logout")
    public String OwnerLogout(HttpSession session) {
        session.setAttribute("username",null);
        return "redirect:/owner_login";
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
            if(!username.equals("") && !password.equals("")) {
                // Creating and saving a new cart for the customer
                Cart cart = new Cart(username);
                cartRepository.save(cart);
                // Creating and saving a new customer
                Customer customer = new Customer(email,phonenumber,username,password,name,address);
                customer.setCart(cart);
                customerRepository.save(customer);
                return "redirect:/customer_login";
            } else {// Handling empty username and password:
                // 1. the controller will show the error message below
                // 2. the controller will stay on the customer_sign page
                model.addAttribute("signup_error", "Username or Password input is empty. Please set something.");
                return "customer_signup";
            }
        } else {// Handling already used username:
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
        Optional<Cart> cartResult = cartRepository.findByUsername(username);
        // Add appropriate handling and redirections based on login success or failure
        if (result.isPresent() && cartResult.isPresent()) {
            Customer customer = result.get();
            String customerPassword = customer.getPassword();
            if(customerPassword.equals(password)) {
                Long cartId = cartResult.get().getId();
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
}