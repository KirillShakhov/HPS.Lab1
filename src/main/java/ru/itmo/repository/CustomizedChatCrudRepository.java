package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Chat;


@Repository
public interface CustomizedChatCrudRepository extends CrudRepository<Chat, Long> {

}
