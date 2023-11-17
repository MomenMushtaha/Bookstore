package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookStoreManagementRepository bookStoreManagementRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID"));
    }

    //----------------------------------------------------------------------------------------------------------------
    //Momin Mushtaha Update
    @GetMapping("/owner_login")
    public String Ownerloadlogin(){
        return "owner_login";
    }

    @PostMapping("/owner_login")
    public String OwnerLogin(@ModelAttribute String username,@ModelAttribute String password){
        Owner tempOwner = ownerRepository.findByUsername(username);
        if(tempOwner.getPassword()!=password){
            return "login_error";
        }else {
            return "index";
        }
    }




    @GetMapping("/owner_signup")
    public String OwnerSignup(Model model){
        model.addAttribute("owner", new Owner());
        return "owner_signup";
    }
    @PostMapping(path = "/owner_signup")
    public String createOwner(@ModelAttribute Owner owner){
        BookStoreManagement ownerBookStore = new BookStoreManagement();
        bookStoreManagementRepository.save(ownerBookStore);
        owner.setOwnersStore(ownerBookStore);
        ownerRepository.save(owner);
        return "users";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", ownerRepository.findAll());
        return "users";
    }

    @GetMapping("/new_book")
    public String createNewBook(Model model){
        model.addAttribute("book", new Book());
        return ""; //
    }
    @PostMapping("/new_book")
    public String startAdding(@ModelAttribute Book book){
        bookRepository.save(book);
        Long id = book.getId();
        return ("redirect:/books/"+id);
    }






    //----------------------------------------------------------------------------------------------------------------
    //Momin Mushtaha Update
    @GetMapping("/customer_login")
    public String CustomerLoadLogin(){
        return "customer_login";
    }

    @PostMapping("/customer_login")
    public String CustomerLogin(@ModelAttribute String username,@ModelAttribute String password){
        Customer tempCustomer = customerRepository.findByUsername(username);
        if(tempCustomer.getPassword()!=password){
            return "login_error";
        }else {
            return "index";
        }
    }




    @GetMapping("/customer_signup")
    public String CustomerSignup(Model model){
        model.addAttribute("customer", new Customer());
        return "customer_signup";
    }
    @PostMapping(path = "/customer_signup")
    public String createCustomer(@ModelAttribute Customer customer){
        customerRepository.save(customer);
        return "users";
    }


}
