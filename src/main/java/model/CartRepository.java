package model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByCustomer(Customer customer);
    Cart findById(long id);

}

