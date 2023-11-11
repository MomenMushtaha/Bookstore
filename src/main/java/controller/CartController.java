package controller;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/view/{cartId}")
    public Cart viewCart(@PathVariable Long cartId) {
        // Replace this with your logic to retrieve and return cart information
        return cartRepository.findById(cartId).orElse(null);
    }

    // Add a book to the cart
    @PostMapping("/add-book")
    public void addBookToCart(@RequestBody Book book, @RequestParam Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.addBook(book);
            cartRepository.save(cart); // Save the updated cart
        }
    }

    // Remove a book from the cart
    @DeleteMapping("/remove-book")
    public void removeBookFromCart(@RequestBody Book book, @RequestParam Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.removeBook(book);
            cartRepository.save(cart); // Save the updated cart
        }
    }

    // Checkout the cart
    @PostMapping("/checkout/{cartId}")
    public void checkoutCart(@PathVariable Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.checkout();
            cartRepository.save(cart); // Save the updated cart
        }
    }
}
