package model;

import org.springframework.data.repository.CrudRepository;

public interface BookStoreManagementRepository extends CrudRepository<BookStoreManagement, Long>{
    BookStoreManagement findById(long id);
}
