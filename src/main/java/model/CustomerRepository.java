package model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Cart, Long> {
    Cart findById(int id);

}

