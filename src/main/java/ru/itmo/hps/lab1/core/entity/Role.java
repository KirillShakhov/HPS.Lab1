package ru.itmo.hps.lab1.core.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = "public")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "roles")
//    Set<User> users;
}
