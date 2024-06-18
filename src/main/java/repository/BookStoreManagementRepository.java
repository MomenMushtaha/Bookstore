package repository;

// Importing necessary libraries
import entity.BookStoreManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is a Repository class for BookStoreManagement entities.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 * This will allow Spring to automatically implement this repository interface
 * in a bean that has CRUD functionality.
 * This repository uses BookStoreManagement as the domain type and Long as the id type.
 */
@Repository
public interface BookStoreManagementRepository extends CrudRepository<BookStoreManagement, Long>{

    /**
     * This method is used to find a BookStoreManagement entity by its id.
     * It returns an Optional that can contain a BookStoreManagement if it exists, or be empty if it does not.
     * @param id The id of the BookStoreManagement entity to retrieve.
     * @return An Optional containing the BookStoreManagement entity if it exists, or empty if it does not.
     */
    Optional<BookStoreManagement> findById(long id);
}