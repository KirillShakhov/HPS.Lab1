package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Role;
import ru.itmo.entity.User;

import java.util.Optional;


@Repository
public interface CustomizedRoleCrudRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String username);
}
