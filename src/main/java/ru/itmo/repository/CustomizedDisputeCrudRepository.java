package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Dispute;


@Repository
public interface CustomizedDisputeCrudRepository extends CrudRepository<Dispute, Long> {

}
