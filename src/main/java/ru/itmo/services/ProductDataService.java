package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Attachment;
import ru.itmo.entity.PageEntity;
import ru.itmo.entity.Product;
import ru.itmo.exeptions.NotFoundException;
import ru.itmo.exeptions.PageNotFoundException;
import ru.itmo.repository.CustomizedAttachmentCrudRepository;
import ru.itmo.repository.CustomizedCategoryCrudRepository;
import ru.itmo.repository.CustomizedProductCrudRepository;
import ru.itmo.repository.CustomizedUserCrudRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.*;


@Service
public class ProductDataService {
    private final CustomizedProductCrudRepository customizedProductCrudRepository;
    private final CustomizedUserCrudRepository customizedUserCrudRepository;
    private final CustomizedCategoryCrudRepository customizedCategoryCrudRepository;
    private final CustomizedAttachmentCrudRepository customizedAttachmentCrudRepository;

    @Autowired
    public ProductDataService(CustomizedProductCrudRepository customizedProductCrudRepository, CustomizedUserCrudRepository customizedUserCrudRepository, CustomizedCategoryCrudRepository customizedCategoryCrudRepository, CustomizedAttachmentCrudRepository customizedAttachmentCrudRepository) {
        this.customizedProductCrudRepository = customizedProductCrudRepository;
        this.customizedUserCrudRepository = customizedUserCrudRepository;
        this.customizedCategoryCrudRepository = customizedCategoryCrudRepository;
        this.customizedAttachmentCrudRepository = customizedAttachmentCrudRepository;
    }

    @Transactional
    public List<Product> findAll() {
        return (List<Product>) customizedProductCrudRepository.findAll();
    }

    @Transactional
    public void save(Product product) {
        customizedProductCrudRepository.save(product);
    }

    @Transactional
    public void removeById(Long id) {
        customizedProductCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Product> getById(Long id) {
        return customizedProductCrudRepository.findById(id);
    }

    public void add(String username, String name, Long categoryId, String description,List<Long> attachment) {
        var user = customizedUserCrudRepository.findByUsername(username);
        if (user.isEmpty()) throw new NotFoundException(String.format("username %s not found", username));
        var category = customizedCategoryCrudRepository.findById(categoryId);
        if (category.isEmpty()) throw new NotFoundException(String.format("category %s not found", categoryId));
        Set<Attachment> attachments = new HashSet<>();
        for (Long attachmentId : attachment){
            var a = customizedAttachmentCrudRepository.findById(attachmentId);
            if (a.isEmpty()) throw new NotFoundException(String.format("attachment %s not found", attachmentId));
            attachments.add(a.get());
        }
        var product = Product.builder()
                                        .name(name)
                                        .user(user.get())
                                        .category(category.get())
                                        .description(description)
                                        .attachments(attachments)
                                        .build();
        customizedProductCrudRepository.save(product);
    }

    public PageEntity getAllAttachment(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<Product> list = customizedProductCrudRepository.findAll(pageable);
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageEntity.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }

}

