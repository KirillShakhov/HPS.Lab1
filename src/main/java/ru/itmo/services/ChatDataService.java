package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Chat;
import ru.itmo.repository.CustomizedChatCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ChatDataService {
    private final CustomizedChatCrudRepository customizedChatCrudRepository;

    @Autowired
    public ChatDataService(CustomizedChatCrudRepository customizedChatCrudRepository) {
        this.customizedChatCrudRepository = customizedChatCrudRepository;
    }

    @Transactional
    public List<Chat> findAll() {
        return (List<Chat>) customizedChatCrudRepository.findAll();
    }

    @Transactional
    public void save(Chat chat) {
        customizedChatCrudRepository.save(chat);
    }

    @Transactional
    public void removeById(Long id) {
        customizedChatCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Chat> getById(Long id) {
        return customizedChatCrudRepository.findById(id);
    }
}

