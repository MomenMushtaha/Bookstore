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


    @PostMapping
    public Owner createOwner(@RequestBody Owner newOwner){

        BookStoreManagement ownerBookStore = newOwner.getOwnersStore();

        bookStoreManagementRepository.save(ownerBookStore);
        System.out.println("bookstore saved");
        return ownerRepository.save(newOwner);
    }
    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner ID"));
    }

}
