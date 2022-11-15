package ru.itmo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "payments", schema = "public")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Payment.class)
    private Long id;

    @NotNull
    @Column(name = "token", nullable = false)
    @JsonView(View.Payment.class)
    private String token;

    @NotNull
    @Column(name = "create_date", nullable = false)
    @JsonView(View.Payment.class)
    private String createDate;

    @Column(name = "description")
    @JsonView(View.Payment.class)
    private String description;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
