package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private BookStoreManagementRepository bookStoreManagementRepository;


    @PostMapping("/owner")
    public Owner createOwner(@RequestBody Owner newOwner){

        BookStoreManagement ownerBookStore = new BookStoreManagement();
        bookStoreManagementRepository.save(ownerBookStore);
        newOwner.setOwnersStore(ownerBookStore);
        return ownerRepository.save(newOwner);
    }
    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID"));
    }

    @PostMapping("/owner/{id}/addBookFront")
    public String addBook(@PathVariable Long id, @ModelAttribute Book book){
        BookStoreManagement ownerBookStore = bookStoreManagementRepository.findById(id).orElseThrow();
        ownerBookStore.addBook(book);
        System.out.println("Test add book");
        bookStoreManagementRepository.save(ownerBookStore);
        return "redirect:/owner";
    }

    //public BookStoreManagement getBookStore(){

    //}

}
