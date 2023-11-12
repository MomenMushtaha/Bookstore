package controller;

import model.BookStoreManagement;
import model.Owner;
import model.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class viewController {

    @Autowired
    OwnerRepository repo;

    @GetMapping("/owners")
    public String owners(Model model){
        model.addAttribute("owner", repo.findById(1));
        return "owners";
    }

    @PostMapping("/createOwnerFront/")
    public Owner createOwner(@RequestBody Owner newOwner){

        return repo.save(newOwner);
    }
}
