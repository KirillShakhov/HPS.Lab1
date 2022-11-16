package ru.itmo.hps.lab1.payment.entity;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
public class Payment {
    private Long id;
    private String token;
    private String createDate;
    private String description;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
