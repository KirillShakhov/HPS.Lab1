package ru.itmo.hps.lab1.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.core.entity.Attachment;


@Repository
public interface CustomizedAttachmentCrudRepository extends JpaRepository<Attachment, Long> {

}
