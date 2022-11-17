package ru.itmo.hps.lab1.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.itmo.hps.lab1.chat.payload.Chat;
import ru.itmo.hps.lab1.chat.repo.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Mono<Chat> getChatById(Long id){
        return chatRepository.findById(id);
    }

}
