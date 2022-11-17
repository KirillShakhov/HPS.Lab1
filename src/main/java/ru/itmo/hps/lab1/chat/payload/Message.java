package ru.itmo.hps.lab1.chat.payload;

import lombok.Data;

@Data
public class Message {

    private Long chat_id;

    private String user_name;

    private String message;

//    private String createDate;
}
