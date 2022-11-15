package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Message;
import ru.itmo.entity.Order;


@Repository
public interface CustomizedOrderCrudRepository extends CrudRepository<Order, Long> {

}
