package repository;

import entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * This interface represents a repository of Cart entities.
 * It extends CrudRepository to provide CRUD operations on Cart entities.
 */
public interface CartRepository extends CrudRepository<Cart, Long> {

    /**
     * Finds a Cart entity in the repository by their assigned id.
     * @param id The id of the Cart entity to find.
     * @return An Optional that may contain the Cart entity if found.
     */
    Optional<Cart> findById(long id);

    /**
     * Deletes a Cart entity in the repository by their assigned id.
     * @param id The id of the Cart entity to delete.
     * @return An Optional that may contain the Cart entity if it was found and deleted.
     */
    Optional<Cart> deleteCartById(long id);
}