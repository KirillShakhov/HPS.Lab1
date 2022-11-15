package ru.itmo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.entity.Category;
import ru.itmo.entity.View;
import ru.itmo.exeptions.AlreadyExistsException;
import ru.itmo.exeptions.PageNotFoundException;
import ru.itmo.payload.ApiResponse;
import ru.itmo.payload.CategoryRequest;
import ru.itmo.payload.SignUpRequest;
import ru.itmo.services.CategoryDataService;
import ru.itmo.services.ProductDataService;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDataService categoryDataService;

    @PostMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryRequest payload) {
        try {
            categoryDataService.add(Category.builder()
                    .name(payload.getName())
                    .shortName(payload.getShortName())
                    .tags(payload.getTags())
                    .build());
            return ResponseEntity.ok(new ApiResponse(true, "Category has been added"));
        }
        catch (AlreadyExistsException e){
            return ResponseEntity.ok(new ApiResponse(false,e.getMessage()));
        }
    }

    @JsonView(View.PageEntity.class)
    @GetMapping(value = "/category")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listProduct(@RequestParam(value = "page") Integer page)  {
        try {
            log.info("retrieving all attachments");
            return ResponseEntity
                    .ok(categoryDataService.getAllAttachment(page));
        }
        catch (PageNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiResponse(false,e.getMessage()));
        }
    }
}
