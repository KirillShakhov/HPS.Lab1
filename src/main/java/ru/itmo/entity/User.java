package ru.itmo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.User.class)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "username", unique = true, nullable = false)
    @JsonView(View.User.class)
    private String username;

    @NotNull
    @Size(max = 100)
    @Column(name = "first_name", nullable = false)
    @JsonView(View.User.class)
    private String firstName;

    @Size(max = 100)
    @Column(name = "last_name")
    @JsonView(View.User.class)
    private String lastName;

    @Email
    @NotNull
    @Column(name = "email", unique = true, nullable = false)
    @JsonView(View.User.class)
    private String email;

    @Column(name = "pass", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_and_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

    @Column(name = "active", nullable = false)
    @JsonView(View.User.class)
    private boolean active = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
