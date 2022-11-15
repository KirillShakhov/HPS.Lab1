package ru.itmo.hps.lab1.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.core.entity.User;

import java.util.Optional;


@Repository
public interface CustomizedUserCrudRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
