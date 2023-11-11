package model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookStoreManagementRepository extends CrudRepository<BookStoreManagement, Integer>{
    BookStoreManagement findById(int id);
}
