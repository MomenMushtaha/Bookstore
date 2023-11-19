package model;

import model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findById(long id);
    Optional<Cart> findByUsername(String username);

}

