package ru.itmo.hps.lab1.chat.payload;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Chat {

    private Long id;

    private String message;

//    private User adminUser;
//
//    Set<User> users;
//
//    Set<Message> messages;

}
