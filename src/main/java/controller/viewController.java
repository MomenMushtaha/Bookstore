package controller;

import model.BookStoreManagement;
import model.BookStoreManagementRepository;
import model.Owner;
import model.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class viewController {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    BookStoreManagementRepository bookStoreRepository;

    @GetMapping("/")
    public String Homepage() {return "HomePage";}

    //@GetMapping("/owners")
    //public String owners(Model model){
        //model.addAttribute("owner", repo.findById(1));
      //  return "owners";
    //}

    //@PostMapping("/createOwnerFront/")
  //  public Owner createOwner(@RequestBody Owner newOwner){
//
     //   return repo.save(newOwner);
   // }
//}




    @RequestMapping("/")
    public @ResponseBody String homepage() {
        Owner owner1 = new Owner("owneremail", "12345", "Owner", "ImTheBoss", "Boss", "bossstreet");
        System.out.println("Owner made");

// Retrieve the BookStoreManagement associated with the owner
        BookStoreManagement owner1BookStore = owner1.getOwnersStore();

// Save the BookStoreManagement to the database
        bookStoreRepository.save(owner1BookStore);
        System.out.println("bookstore saved");

// Save the owner to the database
        ownerRepository.save(owner1);
        System.out.println("owner saved");

        return owner1BookStore.toString();

    }

}