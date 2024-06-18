package repository;

// Importing necessary libraries
import java.util.Optional;
import entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a Repository interface for Book entities.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 * This will allow Spring to automatically implement this repository interface
 * in a bean that has CRUD functionality.
 * This repository uses Book as the domain type and Long as the id type.
 */
@Repository
public interface BookRepository extends CrudRepository <Book, Long>{

    /**
     * This method is used to find a Book entity by its id.
     * @param id The id of the Book entity to retrieve.
     * @return The Book entity if it exists.
     */
    Book findById(long id);

    /**
     * This method is used to find a Book entity by its isbn.
     * It returns an Optional that can contain a Book if it exists, or be empty if it does not.
     * @param isbn The isbn of the Book entity to retrieve.
     * @return An Optional containing the Book entity if it exists, or empty if it does not.
     */
    Optional<Book> findByIsbn(int isbn);

    /**
     * This method is used to retrieve all Book entities, ordered by the 'recommended' field in descending order.
     * @return An Iterable containing all Book entities, ordered by the 'recommended' field in descending order.
     */
    Iterable<Book> findAllByOrderByRecommendedDesc();

    /**
     * This method is used to find Book entities by their isbn.
     * @param isbn The isbn of the Book entities to retrieve.
     * @return An Iterable containing the Book entities if they exist.
     */
    Iterable<Book> findBooksByIsbn(int isbn);

    /**
     * This method is used to find Book entities by their publisher.
     * @param publisher The publisher of the Book entities to retrieve.
     * @return An Iterable containing the Book entities if they exist.
     */
    Iterable<Book> findBooksByPublisher(String publisher);

    /**
     * This method is used to find Book entities by their author.
     * @param author The author of the Book entities to retrieve.
     * @return An Iterable containing the Book entities if they exist.
     */
    Iterable<Book> findBooksByAuthor(String author);

    /**
     * This method is used to find Book entities by their name.
     * @param bookName The name of the Book entities to retrieve.
     * @return An Iterable containing the Book entities if they exist.
     */
    Iterable<Book> findBooksByBookName(String bookName);

    /**
     * This method is used to find a Book entity by its BookId.
     * It returns an Optional that can contain a Book if it exists, or be empty if it does not.
     * @param bookId The BookId of the Book entity to retrieve.
     * @return An Optional containing the Book entity if it exists, or empty if it does not.
     */
    Optional<Book> findById(Book.BookId bookId);

    /**
     * This method is used to delete a Book entity by its isbn.
     * @param isbn The isbn of the Book entity to delete.
     */
    void deleteByIsbn(int isbn);
}