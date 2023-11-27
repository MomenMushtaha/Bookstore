package model;

import entity.Book;
import entity.Customer;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

//Recommendations class
//This class provides the methods to arranging a Customers recommendation list.
//Uses repositories to set the recommendation list
//ToDo Implement into the project (For Milestone 3)
public class Recommendations {
private BookRepository bookRepository;
private CustomerRepository customerRepository;


    public void resetRecommendations(){
        //Reset all books to not recommended
        Iterable<Book> allBooks = bookRepository.findAll();
        for(Book book: allBooks){
            book.setRecommended(false);
            bookRepository.save(book);
        }
    }


    public void setRecommendedBooks(Customer customer){
        //Get All other customer accounts other than current customer
        Iterable<Customer> allCustomers = customerRepository.findAll();
        List<Customer> allOtherCustomers = new ArrayList<>();
        for(Customer c : allCustomers){
            if(!c.equals(customer)){
                allOtherCustomers.add( (Customer) c);
            }
        }

        double thresholdPercentage = 50.0;
        List<Book> purchasedBooks = customer.getPurchaseHistory();
        //For each other customer
        for(Customer otherCustomer : allOtherCustomers){
            List<Book> otherPurchasedBooks = otherCustomer.getPurchaseHistory();
            int counter = 0;
            //Check number of purchasedBooks matching BookIds with current customer purchased books
            for (Book purchasedBook: purchasedBooks){
                if(otherPurchasedBooks.contains(purchasedBook)){
                    counter += 1;
                }
            }
            //Convert to percentage compared to current customer total  number of books
            double similarityPercentage = (double) (counter * 100) / purchasedBooks.size();
            //Check percentage compared to threshold
            if(similarityPercentage >= thresholdPercentage){
                //If true, set all books that the other customer purchased to recommended true
                for (Book otherPurchasedBook: otherPurchasedBooks){
                    //Set recommended only if current customer didn't already purchase the other book
                    if(!purchasedBooks.contains(otherPurchasedBook)){
                        otherPurchasedBook.setRecommended(true);
                        bookRepository.save(otherPurchasedBook);
                    }
                }
            }
        }

    }
}
