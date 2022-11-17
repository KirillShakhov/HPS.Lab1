package ru.itmo.hps.lab1.payment.entity;

import lombok.*;

import java.util.Date;

@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private String name;
    private String token;
    private Date createDate;
    private String description;
}
