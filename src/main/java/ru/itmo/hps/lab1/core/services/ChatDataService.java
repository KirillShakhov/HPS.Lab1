package ru.itmo.hps.lab1.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.hps.lab1.core.entity.Chat;
import ru.itmo.hps.lab1.core.repository.CustomizedChatCrudRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ChatDataService {
    private final CustomizedChatCrudRepository customizedChatCrudRepository;

    @Autowired
    public ChatDataService(CustomizedChatCrudRepository customizedChatCrudRepository) {
        this.customizedChatCrudRepository = customizedChatCrudRepository;
    }

    public List<Chat> findAll() {
        return (List<Chat>) customizedChatCrudRepository.findAll();
    }

    public void save(Chat chat) {
        customizedChatCrudRepository.save(chat);
    }

    public void removeById(Long id) {
        customizedChatCrudRepository.deleteById(id);
    }

    public Optional<Chat> getById(Long id) {
        return customizedChatCrudRepository.findById(id);
    }
}

