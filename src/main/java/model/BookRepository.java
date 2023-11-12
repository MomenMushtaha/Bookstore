package model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book, Long>{

    List<Book> findByBookName(String bookName);

    Book findById(long id);
}
