package controller;

import entity.Cart;
import entity.Book;
import repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling cart-related operations such as viewing the cart, adding or removing books from the cart, and checking out.
 */
@Controller
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    /**
     * Retrieves and returns the cart for the given cart ID.
     * @param cartId The ID of the cart to retrieve.
     * @return The cart with the specified ID, or null if not found.
     */
    @GetMapping("/view/{cartId}")
    public Cart viewCart(@PathVariable Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    /**
     * Adds a book to the cart.
     * @param book The book to add to the cart.
     * @param cartId The ID of the cart to add the book to.
     */
    @PostMapping("/add-book")
    public void addBookToCart(@RequestBody Book book, @RequestParam Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.addBook(book);
            cartRepository.save(cart);
        }
    }

    /**
     * Removes a book from the cart.
     * @param book The book to remove from the cart.
     * @param cartId The ID of the cart to remove the book from.
     */
    @DeleteMapping("/remove-book")
    public void removeBookFromCart(@RequestBody Book book, @RequestParam Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.removeBook(book);
            cartRepository.save(cart);
        }
    }

    /**
     * Checks out the cart, finalizing the purchase.
     * @param cartId The ID of the cart to check out.
     */
    @PostMapping("/checkout/{cartId}")
    public void checkoutCart(@PathVariable Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.checkout();
            cartRepository.save(cart);
        }
    }
}
