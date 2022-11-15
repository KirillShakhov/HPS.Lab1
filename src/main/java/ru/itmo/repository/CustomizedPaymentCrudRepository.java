package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Order;
import ru.itmo.entity.Payment;


@Repository
public interface CustomizedPaymentCrudRepository extends CrudRepository<Payment, Long> {

}
