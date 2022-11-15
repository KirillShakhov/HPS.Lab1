package ru.itmo.controllers;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itmo.entity.InstaUserDetails;
import ru.itmo.entity.View;
import ru.itmo.exeptions.ResourceNotFoundException;
import ru.itmo.payload.ApiResponse;
import ru.itmo.services.UserDataService;
import javax.management.relation.RoleNotFoundException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserDataService userService;

    @JsonView(View.User.class)
    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
        log.info("retrieving user {}", username);
        return  userService
                .getByUsername(username)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(username));
    }

    @JsonView(View.User.class)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        log.info("retrieving all users");
        return ResponseEntity
                .ok(userService.findAll());
    }

    @JsonView(View.User.class)
    @GetMapping(value = "/users/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal InstaUserDetails userDetails) {
        return ResponseEntity
                .ok(userService.getByUsername(userDetails.getUsername()));
    }

    @PostMapping(value = "/users/{username}/role", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addRoleToUser(@PathVariable("username") String username, @RequestParam(value = "role") String role) {
        try {
            userService.addRole(username, role);
            return ResponseEntity.ok(new ApiResponse(true,"Role has been added"));
        } catch (RoleNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse(false,e.getMessage()));
        }
    }
}
