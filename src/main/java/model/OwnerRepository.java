package model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findById(long id);
    Optional<Owner> findByUsername(String username);
}

