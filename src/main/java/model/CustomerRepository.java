package model;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Cart, Long> {
    Cart findById(int id);

}

