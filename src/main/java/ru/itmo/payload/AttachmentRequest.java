package ru.itmo.payload;

import lombok.Data;
import ru.itmo.entity.AttachmentType;

import javax.validation.constraints.NotBlank;


@Data
public class AttachmentRequest {
    @NotBlank
    private String base64;
    @NotBlank
    private AttachmentType type;
}