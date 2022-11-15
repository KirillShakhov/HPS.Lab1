package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Attachment;
import ru.itmo.entity.PageEntity;
import ru.itmo.exeptions.PageNotFoundException;
import ru.itmo.repository.CustomizedAttachmentCrudRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AttachmentDataService {
    private final CustomizedAttachmentCrudRepository customizedAttachmentCrudRepository;

    @Autowired
    public AttachmentDataService(CustomizedAttachmentCrudRepository customizedAttachmentCrudRepository) {
        this.customizedAttachmentCrudRepository = customizedAttachmentCrudRepository;
    }

    public List<Attachment> findAll() {
        return customizedAttachmentCrudRepository.findAll();
    }

    public PageEntity getAllAttachment(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<Attachment> list = customizedAttachmentCrudRepository.findAll(pageable);
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageEntity.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }

    public Attachment add(Attachment attachment) {
        attachment.setCreateDate(String.valueOf(new Date(System.currentTimeMillis())));
        return customizedAttachmentCrudRepository.save(attachment);
    }

    public void removeById(Long id) {
        customizedAttachmentCrudRepository.deleteById(id);
    }

    public Optional<Attachment> getById(Long id) {
        return customizedAttachmentCrudRepository.findById(id);
    }
}

