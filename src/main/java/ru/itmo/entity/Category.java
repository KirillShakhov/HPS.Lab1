package ru.itmo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Category.class)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    @JsonView(View.Category.class)
    private String name;

    @Size(max = 60)
    @Column(name = "short_name")
    @JsonView(View.Category.class)
    private String shortName;

    @ElementCollection
    @CollectionTable(name = "categories_tags", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
