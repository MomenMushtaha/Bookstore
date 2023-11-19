package model;

import java.util.Optional;

import model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository <Book, Long>{

    Book findById(long id);
    Iterable<Book> findBooksByIsbn(int isbn);
    //Find All Books and order by recommended First
    Iterable<Book> findAllByOrderByRecommendedDesc();
    Optional<Book> findByIsbn(int isbn);

}
