package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Attachment;
import ru.itmo.entity.Category;
import ru.itmo.entity.PageEntity;
import ru.itmo.exeptions.AlreadyExistsException;
import ru.itmo.exeptions.PageNotFoundException;
import ru.itmo.repository.CustomizedCategoryCrudRepository;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryDataService {
    private final CustomizedCategoryCrudRepository customizedCategoryCrudRepository;

    @Autowired
    public CategoryDataService(CustomizedCategoryCrudRepository customizedCategoryCrudRepository) {
        this.customizedCategoryCrudRepository = customizedCategoryCrudRepository;
    }

    @Transactional
    public List<Category> findAll() {
        return (List<Category>) customizedCategoryCrudRepository.findAll();
    }

    @Transactional
    public void add(Category category) throws AlreadyExistsException {
        if (customizedCategoryCrudRepository.existsByName(category.getName())) throw new AlreadyExistsException(String.format("Category %s already exists", category.getName()));
        customizedCategoryCrudRepository.save(category);
    }

    @Transactional
    public void removeById(Long id) {
        customizedCategoryCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Category> getById(Long id) {
        return customizedCategoryCrudRepository.findById(id);
    }

    public PageEntity getAllAttachment(@NotNull Integer page) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(page, 50);
        Page<Category> list = customizedCategoryCrudRepository.findAll(pageable);
        if (list.isEmpty()){
            throw new PageNotFoundException(String.format("Page %s not found", page));
        }
        return PageEntity.builder()
                .items(list.getContent())
                .hasMore(list.hasNext())
                .build();
    }
}

