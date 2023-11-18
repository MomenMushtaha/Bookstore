package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookStoreManagementRepository bookStoreManagementRepository;
    @Autowired
    private CustomerRepository customerRepository;




}
