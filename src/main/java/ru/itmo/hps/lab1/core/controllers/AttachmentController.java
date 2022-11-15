package ru.itmo.hps.lab1.core.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hps.lab1.core.entity.Attachment;
import ru.itmo.hps.lab1.core.entity.View;
import ru.itmo.hps.lab1.core.exeptions.AlreadyExistsException;
import ru.itmo.hps.lab1.core.exeptions.PageNotFoundException;
import ru.itmo.hps.lab1.core.payload.ApiResponse;
import ru.itmo.hps.lab1.core.payload.AttachmentRequest;
import ru.itmo.hps.lab1.core.services.AttachmentDataService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentDataService attachmentDataService;

    @PostMapping(value = "/attachment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addAttachment(@Valid @RequestBody AttachmentRequest payload) {
        try {
            return ResponseEntity.ok(attachmentDataService.add(Attachment.builder()
                            .base64(payload.getBase64())
                            .type(payload.getType())
                            .build()));
        }
        catch (AlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @JsonView(View.PageEntity.class)
    @GetMapping(value = "/attachment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listCategory(@RequestParam(value = "page") Integer page)  {
        try {
            log.info("retrieving all attachments");
            return ResponseEntity
                    .ok(attachmentDataService.getAllAttachment(page));
        }
        catch (PageNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiResponse(false,e.getMessage()));
        }

    }
}
