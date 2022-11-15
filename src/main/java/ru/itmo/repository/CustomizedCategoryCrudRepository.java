package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Category;


@Repository
public interface CustomizedCategoryCrudRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
