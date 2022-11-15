package ru.itmo.hps.lab1.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.core.entity.Message;


@Repository
public interface CustomizedMessageCrudRepository extends JpaRepository<Message, Long> {

}
