package controller;

import entity.Book;
import entity.BookStoreManagement;
import entity.Owner;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.BookRepository;
import repository.BookStoreManagementRepository;
import repository.OwnerRepository;

import java.util.Optional;

@Controller
public class OwnerController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    BookStoreManagementRepository bookStoreRepository;

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

    @GetMapping("/owner_portal")
    public String Owner(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            return "redirect:/owner_login";
        }

        Optional<BookStoreManagement> bookStoreManagementOptional = bookStoreRepository.findById(owner.get().getId());
        if (bookStoreManagementOptional.isEmpty()) {
            return "redirect:/owner_login";
        }

        BookStoreManagement bookStoreManagement = bookStoreManagementOptional.get();
        Iterable<Book> books = bookStoreManagement.getBookList();

        model.addAttribute("owner", owner.get());
        model.addAttribute("books", books);
        return "owner_portal";
    }

    @GetMapping("/upload_book")
    public String UploadBook(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            return "redirect:/owner_login";
        }

        model.addAttribute("owner", owner.get());
        return "upload_book";
    }

    @PostMapping(value = "/upload_book", params = "AddBook")
    public String UploadBookControl(
            @RequestParam(name = "isbn", required = false, defaultValue = "") int isbn,
            @RequestParam(name = "bookName", required = false, defaultValue = "") String bookName,
            @RequestParam(name = "publisher", required = false, defaultValue = "") String publisher,
            @RequestParam(name = "author", required = false, defaultValue = "") String author,
            @RequestParam(name = "quantity", required = false, defaultValue = "") String quantity,
            @RequestParam(name = "price", required = false, defaultValue = "") String price,
            HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            return "redirect:/owner_login";
        }

        Optional<BookStoreManagement> bookStoreManagementOptional = bookStoreRepository.findById(owner.get().getId());
        if (bookStoreManagementOptional.isEmpty()) {
            return "redirect:/owner_login";
        }

        BookStoreManagement bookStoreManagement = bookStoreManagementOptional.get();
        model.addAttribute("owner", owner.get());

        if (Integer.toString(isbn).isEmpty() || bookName.isEmpty() || author.isEmpty() || publisher.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            model.addAttribute("upload_book_error", "some inputs are missing!");
            return "upload_book";
        }

        try {
            float newPrice = Float.parseFloat(price);
            int newQuantity = Integer.parseInt(quantity);

            if (newPrice >= 0 && newQuantity >= 0) {

                Book newBook = new Book(isbn, bookStoreManagement.getBookList().size() + 1, bookName, author, publisher, newQuantity, newPrice);

                bookStoreManagement.addBook(newBook);
                bookRepository.save(newBook);
                bookStoreRepository.save(bookStoreManagement);

                return "redirect:/owner_portal";
            } else {
                model.addAttribute("upload_book_error", "Please only enter non-negative numbers!");
            }
        } catch (NumberFormatException nfe) {
            model.addAttribute("upload_book_error", "Formatting error, some inputs do not have correct formatting");
        }
        return "upload_book";
    }

    @PostMapping("/edit_book")
    public String editBook(
            @RequestParam(name = "isbn", required = true) int isbn,
            @RequestParam(name = "quantity", required = false, defaultValue = "0") int quantity,
            @RequestParam(name = "edit_book", required = false) String editBook,
            @RequestParam(name = "delete_book", required = false) String deleteBook,
            HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);

        if (owner.isEmpty()) {
            return "redirect:/owner_login";
        }

        model.addAttribute("owner", owner.get());

        try {
            Optional<BookStoreManagement> bookStoreManagementOptional = bookStoreRepository.findById(owner.get().getId());

            if (bookStoreManagementOptional.isEmpty()) {
                return "redirect:/owner_login";
            }

            // Use the provided methods to edit or delete a book
            BookStoreManagement bookStoreManagement = bookStoreManagementOptional.get();
            if (editBook != null) {
                // Edit Book
                bookStoreManagement.updateQuantity(isbn, quantity);
            } else if (deleteBook != null) {
                // Delete Book
                bookStoreManagement.removeBook(isbn);
            }

            // Save the changes to the book store
            bookStoreRepository.save(bookStoreManagement);

            // Save the changes to the book repository
            for (Book book : bookStoreManagement.getBookList()) {
                if (book.getIsbn() == isbn && book.getVersion() == bookStoreManagement.getBookList().size()) {
                    bookRepository.save(book);
                    break;  // Assuming there is only one book with the specified ISBN and version
                }
            }

            return "redirect:/owner_portal";
        } catch (NumberFormatException nfe) {
            model.addAttribute("book_search_error", "Formatting error, ISBN should be a number");
            return "edit_book";
        }
    }


}
