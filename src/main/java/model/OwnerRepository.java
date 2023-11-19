package model;

import model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findById(long id);
    Optional<Owner> findByUsername(String username);
}

