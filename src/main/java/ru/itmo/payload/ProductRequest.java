package ru.itmo.payload;

import lombok.Data;
import ru.itmo.entity.Category;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Long category;
    @NotBlank
    private String description;
    @NotBlank
    private List<Long> attachment;
}