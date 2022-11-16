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

    public Payment(long id, String token, String description, String createDate) {
        this.id = id;
        this.token = token;
        this.description = description;
        this.createDate = createDate;
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    @Override
    public String toString() {
        return "Payment [id=" + id + ", token=" + token + ", desc=" + description + ", createDate=" + createDate + "]";
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String description) {
        this.createDate = createDate;
    }

}
