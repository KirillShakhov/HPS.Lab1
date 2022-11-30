package ru.itmo.hps.lab1.attachment.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.itmo.hps.lab1.attachment.entity.Attachment;

public interface ReactiveAttachmentRepository extends ReactiveCrudRepository<Attachment, String> {


}
