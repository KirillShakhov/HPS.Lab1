package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Attachment;


@Repository
public interface CustomizedAttachmentCrudRepository extends JpaRepository<Attachment, Long> {

}
