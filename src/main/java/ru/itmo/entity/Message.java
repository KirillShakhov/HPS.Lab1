package ru.itmo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "messages", schema = "public")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Message.class)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @JsonView(View.Message.class)
    @ToString.Exclude
    private User user;

    @NotNull
    @Column(name = "message", nullable = false)
    @JsonView(View.Message.class)
    private String message;

    @NotNull
    @Column(name = "create_date", nullable = false)
    @JsonView(View.Message.class)
    private String createDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "message_attachments",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    @ToString.Exclude
    private Set<Attachment> attachments;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
