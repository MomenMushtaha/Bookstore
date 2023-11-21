package controller;
import entity.*;
import repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer newCustomer){
        Cart customerCart = new Cart();
        cartRepository.save(customerCart);
        newCustomer.setCart(customerCart);
        return customerRepository.save(newCustomer);
    }

    @GetMapping("/{customerId}")
    public Customer viewCustomer(@PathVariable Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @GetMapping("/{customerId}/purchase-history")
    public List<Book> viewPurchaseHistory(@PathVariable Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return customer.getPurchaseHistory();
        }
        return null;
    }
    @PostMapping("/{customerId}/add-to-purchase-history")
    public void addToPurchaseHistory(@PathVariable Integer customerId, @RequestBody Book book) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            customer.addToPurchaseHistory(book);
            customerRepository.save(customer);
        }
    }


    @GetMapping("/{customerId}/view-cart")
    public Cart viewCart(@PathVariable Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return customer.getCart();
        }
        return null;
    }

    @PostMapping("/{customerId}/checkout")
    public void checkout(@PathVariable Integer customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer !=null){
            customer.checkout();
            customerRepository.save(customer);
        }
    }
}
