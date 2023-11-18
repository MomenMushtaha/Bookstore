package controller;

import jakarta.servlet.http.HttpSession;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class viewController {

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookStoreManagementRepository bookStoreRepository;

    @GetMapping("/")
    public String Homepage() {return "HomePage";}

    //@GetMapping("/owners")
    //public String owners(Model model){
        //model.addAttribute("owner", repo.findById(1));
      //  return "owners";
    //}

    //@PostMapping("/createOwnerFront/")
  //  public Owner createOwner(@RequestBody Owner newOwner){
//
     //   return repo.save(newOwner);
   // }
//}




    @RequestMapping("/")
    public @ResponseBody String homepage() {
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

        return owner1BookStore.toString();

    }


    @GetMapping("/customer_signup")
    public String CustomerSignup() {return "customer_signup";}

    @PostMapping("/customer_signup")
    public String CustomerSignupControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            @RequestParam(name="name", required=false, defaultValue="") String name,
            @RequestParam(name="address", required=false, defaultValue="") String address,
            @RequestParam(name="email", required=false, defaultValue="") String email,
            @RequestParam(name="phoneNumber", required=false, defaultValue="") String phoneNumber,
            Model model) {
        // does username already exist
        Optional<Customer> result = customerRepository.findByUsername(username);
        if (result.isEmpty()) {
            if(!username.equals("") && !password.equals("")) {
                Cart cart = new Cart(username);
                cartRepository.save(cart);

                Customer customer = new Customer(email,phoneNumber,username,password,name,address);
                customer.setCart(cart);

                customerRepository.save(customer);
                return "redirect:/customer_login";
            }
            //Some Input not set
            else {
                model.addAttribute("signup_error", "Username or Password input is empty. Please set something.");
                return "customer_signup";
            }
        }
        //Username already exits
        else {
        model.addAttribute("signup_error", "Username already used, choose a different username!");
            return "customer_signup";
        }
    }


    @GetMapping("/customer_login")
    public String CustomerLogin() {
        return "customer_login";
    }

    @PostMapping( value = "/customer_login", params = "customer_login")
    public String checkCustomerLogin(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
                    HttpSession session, Model model) {
        Optional<Customer> result = customerRepository.findByUsername(username);
        Optional<Cart> cartResult = cartRepository.findByUsername(username);
        if (result.isPresent() && cartResult.isPresent()) {
            Customer customer = result.get();
            String customerPassword = customer.getPassword();
            if(customerPassword.equals(password)) {
                Long cartId = cartResult.get().getId();
                model.addAttribute("username", username);
                model.addAttribute("cartId", cartId);
                session.setAttribute("username", username);
                session.setAttribute("cartId", cartId);
                return "redirect:/shop";
            }
        }
        model.addAttribute("login_error", "Invalid username or password");
        return "customer_login";
    }



    @GetMapping("/owner_signup")
    public String OwnerSignup() {return "owner_signup";}

    @PostMapping("/owner_signup")
    public String OwnerSignupControl(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            @RequestParam(name="name", required=false, defaultValue="") String name,
            @RequestParam(name="address", required=false, defaultValue="") String address,
            @RequestParam(name="email", required=false, defaultValue="") String email,
            @RequestParam(name="phoneNumber", required=false, defaultValue="") String phoneNumber,
            Model model) {
        // does username already exist
        Optional<Owner> result = ownerRepository.findByUsername(username);
        if (result.isEmpty()) {
            if(!username.equals("") && !password.equals("")) {
                Owner owner = new Owner(name,phoneNumber,username,password,email,phoneNumber);
                BookStoreManagement ownerBookStore = new BookStoreManagement();
                bookStoreRepository.save(ownerBookStore);
                owner.setOwnersStore(ownerBookStore);
                ownerRepository.save(owner);
                return "redirect:/owner_login";
            }
            //Some Input not set
            else {
                model.addAttribute("signup_error", "Username or Password input is empty. Please set something.");
                return "owner_signup";
            }
        }
        //Username already exits
        else {
            model.addAttribute("signup_error", "Username already used, choose a different username!");
            return "owner_signup";
        }
        }





    @GetMapping("/owner_login")
    public String OwnerLogin() {
        return "owner_login";
    }

    @PostMapping( value = "/owner_login", params = "owner_login")
    public String checkOwnerLogin(
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
        return "owner_login";}

}