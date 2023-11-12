package model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findByCustomer(Customer customer);
    Cart findById(int id);

}

