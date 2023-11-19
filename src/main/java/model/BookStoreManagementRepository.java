package model;

import model.BookStoreManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreManagementRepository extends CrudRepository<BookStoreManagement, Long>{
    BookStoreManagement findById(long id);
}
