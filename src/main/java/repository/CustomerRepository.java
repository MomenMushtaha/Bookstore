package repository;

import entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * This interface represents a repository of Customer entities.
 * It extends CrudRepository to provide CRUD operations on Customer entities.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    /**
     * Finds a Customer entity in the repository by their assigned id.
     * @param id The id of the Customer entity to find.
     * @return An Optional that may contain the Customer entity if found.
     */
    Optional<Customer> findById(long id);

    /**
     * Finds a Customer entity in the repository by their username.
     * @param username The username of the Customer entity to find.
     * @return An Optional that may contain the Customer entity if found.
     */
    Optional<Customer> findByUsername(String username);
}
