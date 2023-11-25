package controller;

import repository.BookRepository;
import repository.OwnerRepository;
import entity.Owner;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class OwnerController {
    // Injecting dependencies for book and owner repositories
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OwnerRepository ownerRepository;



    /**
     * Method to display the owner portal, only accessible to logged-in owners
     * @param model
     * @param session
     * @return a direction to the next appropriate page
     */
    @GetMapping("/owner_portal")
    public String Owner(Model model, HttpSession session) {
        // Retrieve the username from the session
        String username = (String) session.getAttribute("username");
        // Fetch the owner details from the repository
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        // If no owner is found, redirect to the login page
        if (owner.isEmpty()) {return "redirect:/owner_login";}
        // Fetch all books and add them to the model for display
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("owner", owner.get());
        model.addAttribute("books", books);
        return "owner_portal";
    }




    /**
     * Method UploadBook to navigate to the book upload page
     * @param model
     * @param session
     * @return a direction to the next appropriate page
     */
    @GetMapping("/upload_book")
    public String UploadBook(HttpSession session, Model model) {
        // Retrieve the username from the session
        String username = (String) session.getAttribute("username");
        // Fetch the owner details from the repository
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        // If no owner is found, redirect to the login page
        if (owner.isEmpty()) {return "redirect:/owner_login";}
        // else navigate to the upload_book page
        model.addAttribute("owner", owner.get());
        return "upload_book";
    }


    /**
     * Method UploadBookControl to handle the uploading of a new book by the owner
     * @param session
     * @param model
     * @return a direction to the next appropriate page
     */
    @PostMapping(value="/upload_book", params = "AddBook")
    public String UploadBookControl(
            @RequestParam(name="isbn", required=false, defaultValue = "") int isbn,
            @RequestParam(name="bookname", required=false, defaultValue = "") String bookname,
            @RequestParam(name="publisher", required=false, defaultValue = "") String publisher,
            @RequestParam(name="author", required=false, defaultValue = "") String author,
            @RequestParam(name="quantity", required=false, defaultValue = "") String quantity,
            @RequestParam(name="price", required=false, defaultValue = "") String price, HttpSession session, Model model) {


        // Retrieve the username from the session
        String username = (String) session.getAttribute("username");
        // Fetch the owner details from the repository
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        // If no owner is found, redirect to the login page
        if (owner.isEmpty()) {return "redirect:/owner_login";}
        model.addAttribute("owner", owner.get());
        // Handle missing input fields
        if (Integer.toString(isbn).equals("") || bookname.equals("") || author.equals("") || publisher.equals("") || quantity.equals("") || price.equals("")) {
            model.addAttribute("upload_book_error", "some inputs are missing!");
            return "upload_book";
        }
        // Check if a book with the same ISBN exists and handle new variant creation
        Iterable<Book> bookList = bookRepository.findBooksByIsbn(isbn);
        int booksIdenticalIsbn = 0;
        for( Book book : bookList){
            booksIdenticalIsbn++;
        }

        try {
            // Parse and validate numerical input for price and quantity
            double newPrice = Double.parseDouble(price);
            int newQuantity = Integer.parseInt(quantity);
            // Handle negative values for price and quantity
            if (newPrice >= 0 && newQuantity >= 0) {
                int version = booksIdenticalIsbn + 1;
                Book newBook = new Book(isbn, version, bookname, author, publisher, newQuantity, newPrice);
                bookRepository.save(newBook);
                return "redirect:/owner_portal";
            } else {
                model.addAttribute("upload_book_error", "Please only enter non-negative numbers!");
            }
        } catch (NumberFormatException nfe) {
            model.addAttribute("upload_book_error", "formatting error, some inputs do not have correct formatting");
        }
        return "upload_book";
    }


    /**
     * Method EditBook to navigate to the book edit page
     * @param session
     * @param model
     * @return a direction to the next appropriate page
     */
    @GetMapping("/edit_book")
    public String EditBook(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {return "redirect:/owner_login";}
        model.addAttribute("owner", owner.get());
        return "edit_book";
    }


    /**
     * Method EditBookControl to handle the editing of an existing book
     * @param session
     * @param model
     * @return a direction to the next appropriate page
     */
    @PostMapping(value="/edit_book", params="edit_book")
    public String EditBookControl(
            @RequestParam(name="isbn", required=true, defaultValue = "") int isbn,
            @RequestParam(name="version", required=true, defaultValue = "0") String version,
            @RequestParam(name="bookname", required=false, defaultValue = "") String bookname,
            @RequestParam(name="publisher", required=false, defaultValue = "") String publisher,
            @RequestParam(name="author", required=false, defaultValue = "") String author,
            @RequestParam(name="quantity", required=false, defaultValue = "") String quantity,
            @RequestParam(name="price", required=false, defaultValue = "") String price, HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        Optional<Owner> owner = ownerRepository.findByUsername(username);
        if (owner.isEmpty()) {return "redirect:/owner_login";}
        model.addAttribute("owner", owner.get());
        try {
            // Check if the book exists and handle field validation
            Optional<Book> book = bookRepository.findByIsbn(isbn);
            if (Integer.toString(isbn).equals("") || version.equals("0") || bookname.equals("") || publisher.equals("") || author.equals("") || quantity.equals("") || price.equals("")) {
                model.addAttribute("book_search_error", "some inputs are missing!");
                return "owner_edit";
            }
            if (book.isEmpty()) {
                model.addAttribute("book_search_error", "Please enter a valid isbn and version found in the repository!");
                return "owner_edit";
            }
            double newPrice = Double.parseDouble(price);
            int newQuantity = Integer.parseInt(quantity);
            // Validate and parse numerical input
            if (newPrice >= 0 && newQuantity >= 0) {
                //Update book details and redirection
                Book newBook = new Book(isbn, Integer.parseInt(version), bookname, author, publisher, Integer.parseInt(quantity), Integer.parseInt(price));
                bookRepository.save(newBook);
                return "redirect:/owner_portal";
            } else {
                //error handling for negative values
                model.addAttribute("book_search_error", "Please only enter non-negative numbers!");
                return "owner_edit";
            }
        } catch (NumberFormatException nfe) {
            //error handling for incorrect number format
            model.addAttribute("book_search_error", "formatting error, some inputs do not have correct formatting");
            return "owner_edit";
        }
    }
}
