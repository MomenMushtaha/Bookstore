package controller;

import model.*;
import entity.*;
import repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;



@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CartRepository cartRepository;


    @GetMapping("/bookstore_portal")
    public String customer(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {return "redirect:/customer_login";}
        //recommendation code goes here
        //ex. Iterable<Book> recommendedBooks = bookRepository.findAllByOrderByRecommendedDesc();
        //model.addAttribute("recommended_books", recommendedBooks);
        model.addAttribute("customer", customer);
        return "bookstore_portal";
    }


    // the param is used to specify what is the thing the input or the operation in the html file will be invoked on
    //ex.
    // <label for="search">Search For:</label>
    // <input type="text" id="search" name="search" value="" /><br />
    // <input type="submit" value="Search Book" name="search_book"/><br />
    // <input type="submit" value="View All Books" name="all_books"/>
    @PostMapping(value="/bookstore_portal", params = "all_books")
    public String AllBooks(Model model, HttpSession session){
        //recommendation code goes here
        Iterable<Book> books = bookRepository.findAllByOrderByRecommendedDesc();
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {return "redirect:/customer_login";}

        model.addAttribute("books", books);
        model.addAttribute("customer", customer.get());
        return "bookstore_portal";
    }


    @PostMapping(value="/bookstore_portal", params = "search_book")
    public String SearchBook(
            @RequestParam(name="search", required=false, defaultValue = "") String search,
            @RequestParam(name="filter", required=false, defaultValue = "") String filter,
            Model model, HttpSession session){
        if(!search.isEmpty()){
            Iterable<Book> books = switch (filter) {
                case "by-publisher" -> bookRepository.findBooksByPublisher(search);
                case "by-author" -> bookRepository.findBooksByAuthor(search);
                case "by-name" -> bookRepository.findBooksByBookName(search);
                default -> bookRepository.findAllByOrderByRecommendedDesc();
            };
            //If any book is Found
            if(books.iterator().hasNext()){model.addAttribute("books",books);}
            else {model.addAttribute("search_error", "No Books Found With that Name.");}
        } else{model.addAttribute("search_error", "The Search Bar is Empty");}

        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {return "redirect:/customer_login";}
        model.addAttribute("customer", customer.get());
        return "bookstore_portal";
    }


    @PostMapping(value="/bookstore_portal", params = "buy_book")
    public String BuyBook(
            @RequestParam(name="isbn", required=false, defaultValue = "") int isbn, HttpSession session, Model model){
        Optional<Book> books = bookRepository.findByIsbn(isbn);
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if(books.isPresent() && customer.isPresent()){
            model.addAttribute("customer", customer.get());
            model.addAttribute("book", books.get());
            return "store_item";
        }
        else{
            return "redirect:/bookstore_portal";
        }
    }


    @GetMapping("/store_item")
    public String getStoreItem() {
        return "redirect:/bookstore_portal";
    }

    @PostMapping(value="/store_item", params = "bookstore_page")
    public String BookStorePortal(){
        return "redirect:/bookstore_portal";
    }

    // Method to add a book to the cart
    @PostMapping(value="/store_item", params = "add_to_cart")
    public String AddToCart(
            @RequestParam(name="isbn", required=false, defaultValue = "") int isbn,
            @RequestParam(name="version", required=false, defaultValue = "") int version,
            @RequestParam(name="quantity", required=false, defaultValue = "") int quantity,
            HttpSession session){
        // Fetch customer and book
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        Long cartId = customer.get().getCart().getId();
        //Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<Book> book = bookRepository.findByIsbn(isbn);

        if (book.isPresent() && customer.isPresent() && cart.isPresent()) {
            Book bookToAdd = book.get();
            Customer currentCustomer = customer.get();
            Cart currentCart = cart.get();

            // Adjusting book quantity
            bookToAdd.addToCart(quantity);
            // Adding book to the cart
            currentCart.addBook(bookToAdd);

            bookRepository.save(bookToAdd);
            cartRepository.save(currentCart);

            return "redirect:/bookstore_portal";
        } else {
            return "redirect:/bookstore_portal";
        }
    }


    // Method to display the shopping cart
    @GetMapping("/cart")
    public String shoppingCart(Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {return "redirect:/customer_login";}
        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);
        List<Book> items = new ArrayList<>();
        if (cart.isPresent()) {
            items.addAll(cart.get().getItems());
        }
        model.addAttribute("customer", customer.get());
        model.addAttribute("cart_items", items);
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {
            return "redirect:/customer_login";
        }

        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);

        double price = 0.0;
        if (cart.isPresent()) {
            for (Book book : cart.get().getItems()) {
                price += book.getPrice() * book.getQuantity(); // Calculate price based on cart quantity
            }
        }

        model.addAttribute("customer", customer.get());
        model.addAttribute("cart_items", cart.isPresent() ? cart.get().getItems() : Collections.emptyList());
        model.addAttribute("total_cost", String.format("%.2f", price));
        return "checkout";
    }


    @Transactional
    @PostMapping(value="/checkout", params="checkout")
    public String confirmCheckout(HttpSession session) {
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);

        if (cart.isPresent() && customer.isPresent()) {
            Customer c = customer.get();
            for (Book book : cart.get().getItems()) {
                c.addToPurchaseHistory(book);
                book.removeFromCart(book.getQuantity()); // Reset cart quantity
                bookRepository.save(book);
            }

            cart.get().clearCart();
            cartRepository.save(cart.get());
            // Create new empty Cart for customer
            /*
            cartRepository.deleteCartById(cartId);
            Cart emptyCart = new Cart(c);
            cartRepository.save(emptyCart);
            c.setCart(emptyCart);
            customerRepository.save(c);

             */
            session.setAttribute("cartId", cart.get().getId());
        }

        return "redirect:/customer_purchased_history";
    }


    @GetMapping("/customer_purchased_history")
    public String purchasedBooks(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {
            return "redirect:/customer_login";
        }

        Customer c = customer.get();
        List<Book> books = c.getPurchaseHistory();
        model.addAttribute("empty_books", books.isEmpty() ? "You have not purchased any books yet." : null);
        model.addAttribute("customer", c);
        model.addAttribute("books", books);
        return "customer_purchased_history";
    }


    @GetMapping("/customer_logout")
    public String CustomerLogout(HttpSession session) {
        session.setAttribute("username",null);
        session.setAttribute("cartId",null);
        return "redirect:/customer_login";
    }

}