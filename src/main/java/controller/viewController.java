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

/**
 * This class is a controller that handles HTTP requests related to the view.
 */
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
     * Handles GET requests to the root URL ("/") and returns the name of the view (home_portal) to be rendered.
     * @return The name of the view to be rendered.
     */
    @GetMapping("/")
    public String Home() {
        return "home_portal";
    }

    /**
     * Handles GET requests to "/owner_signup" and returns the name of the view (owner_signup) to be rendered.
     * @return The name of the view to be rendered.
     */
    @GetMapping("/owner_signup")
    public String OwnerSignup() {
        return "owner_signup";
    }

    /**
     * Handles the owner signup form submission.
     * @param username The username of the owner.
     * @param password The password of the owner.
     * @param name The name of the owner.
     * @param address The address of the owner.
     * @param email The email of the owner.
     * @param phonenumber The phone number of the owner.
     * @param model The model to add attributes for the view.
     * @return A direction to the next appropriate page.
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
        Optional<Owner> result = ownerRepository.findByUsername(username);
        if (result.isEmpty()) {
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
        }
    }

    /**
     * Directs users to the owner login page.
     * @return A direction to the next appropriate page.
     */
    @GetMapping("/owner_login")
    public String OwnerLogin() {
        return "owner_login";
    }

    /**
     * Handles the owner login form submission.
     * @param username The username of the owner.
     * @param password The password of the owner.
     * @param session The HTTP session to store user attributes.
     * @param model The model to add attributes for the view.
     * @return A direction to the next appropriate page.
     */
    @PostMapping( value = "/owner_login", params = "owner_login")
    public String OwnerLoginControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            HttpSession session, Model model) {
        Optional<Owner> result = ownerRepository.findByUsername(username);
        if (result.isPresent()) {
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
     * Handles owner logout.
     * @param session The HTTP session to invalidate.
     * @return A direction to the owner login page.
     */
    @GetMapping("/owner_logout")
    public String OwnerLogout(HttpSession session) {
        session.setAttribute("username", null);
        return "redirect:/owner_login";
    }

    /**
     * Creates a sample owner and bookstore management entity upon accessing the root URL.
     * @return The details of the bookstore management entity.
     */
    @RequestMapping("/")
    public @ResponseBody String HomeInitialize() {
        Owner owner1 = new Owner("owneremail@gmail.com", "6132113454", "Owner", "ImTheBoss", "Boss", "921bossstreet");
        System.out.println("owner user created");
        BookStoreManagement owner1BookStore = owner1.getOwnersStore();
        bookStoreRepository.save(owner1BookStore);
        System.out.println("bookstore saved");
        ownerRepository.save(owner1);
        System.out.println("owner user saved");
        return owner1BookStore.toString();
    }

    /**
     * Directs users to the customer signup page.
     * @return A direction to the next appropriate page.
     */
    @GetMapping("/customer_signup")
    public String CustomerSignup() {
        return "customer_signup";
    }

    /**
     * Handles the customer signup form submission.
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param name The name of the customer.
     * @param address The address of the customer.
     * @param email The email of the customer.
     * @param phonenumber The phone number of the customer.
     * @param model The model to add attributes for the view.
     * @return A direction to the next appropriate page.
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
        Optional<Customer> result = customerRepository.findByUsername(username);
        if (result.isEmpty()) {
            if (!username.isEmpty() && !password.isEmpty()) {
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
                model.addAttribute("signup_error", "Username or Password input is empty. Please set something.");
                return "customer_signup";
            }
        } else {
            model.addAttribute("signup_error", "Username already used, choose a different username!");
            return "customer_signup";
        }
    }

    /**
     * Directs users to the customer login page.
     * @return A direction to the next appropriate page.
     */
    @GetMapping("/customer_login")
    public String CustomerLogin() {
        return "customer_login";
    }

    /**
     * Handles the customer login form submission.
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param session The HTTP session to store user attributes.
     * @param model The model to add attributes for the view.
     * @return A direction to the next appropriate page.
     */
    @PostMapping( value = "/customer_login", params = "customer_login")
    public String CustomerLoginControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            HttpSession session, Model model) {
        Optional<Customer> result = customerRepository.findByUsername(username);
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

    /**
     * Directs owners to the edit book page if they are logged in.
     * @param session The HTTP session to check user attributes.
     * @param model The model to add attributes for the view.
     * @return A direction to the edit book page or login page.
     */
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
