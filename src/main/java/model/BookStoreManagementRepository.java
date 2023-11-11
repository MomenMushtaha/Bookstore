package model;

import org.springframework.data.repository.CrudRepository;

public interface BookStoreManagementRepository extends CrudRepository<BookStoreManagement, Integer>{
    BookStoreManagement findById(int id);
}
