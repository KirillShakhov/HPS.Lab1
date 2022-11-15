package ru.itmo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photos", schema = "public")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Attachment.class)
    private Long id;

    @NotNull
    @Column(name = "base64", nullable = false)
    @JsonView(View.Attachment.class)
    private String base64;

    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonView(View.Attachment.class)
    @ToString.Exclude
    private AttachmentType type;

    @NotNull
    @Column(name = "create_date", nullable = false)
    @JsonView(View.Attachment.class)
    private String createDate;

    @ManyToMany(mappedBy = "attachments")
    List<Product> products;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
