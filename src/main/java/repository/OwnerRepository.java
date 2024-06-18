package repository;

import entity.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository class for Owner entity.
 * This interface extends Spring Data JPA's CrudRepository interface which provides basic CRUD operations.
 * Each Owner entity is assigned a Long id.
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    /**
     * Find an Owner in the repository by their assigned id.
     * @param id The id of the Owner to be found.
     * @return An Optional of Owner. If an Owner with the given id is found, it is returned. Otherwise, Optional.empty() is returned.
     */
    Optional<Owner> findById(long id);

    /**
     * Find an Owner in the repository by their username.
     * @param username The username of the Owner to be found.
     * @return An Optional of Owner. If an Owner with the given username is found, it is returned. Otherwise, Optional.empty() is returned.
     */
    Optional<Owner> findByUsername(String username);
}