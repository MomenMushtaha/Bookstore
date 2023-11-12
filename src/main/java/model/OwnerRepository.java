package model;

import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {
    Owner findById(int id);

}

