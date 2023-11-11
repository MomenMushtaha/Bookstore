package model;

import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Cart, Long> {
    Cart findById(int id);

}

