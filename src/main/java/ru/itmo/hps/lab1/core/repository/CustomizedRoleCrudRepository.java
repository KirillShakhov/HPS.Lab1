package ru.itmo.hps.lab1.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.core.entity.Role;

import java.util.Optional;


@Repository
public interface CustomizedRoleCrudRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String username);
}
