package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Message;


@Repository
public interface CustomizedMessageCrudRepository extends CrudRepository<Message, Long> {

}
