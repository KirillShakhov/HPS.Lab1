package ru.itmo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itmo.entity.User;
import ru.itmo.exeptions.AlreadyExistsException;
import ru.itmo.exeptions.BadRequestException;
import ru.itmo.exeptions.StandardRoleNotFoundException;
import ru.itmo.exeptions.NotFoundException;
import ru.itmo.payload.ApiResponse;
import ru.itmo.payload.JwtAuthenticationResponse;
import ru.itmo.payload.LoginRequest;
import ru.itmo.payload.SignUpRequest;
import ru.itmo.services.UserDataService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserDataService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());
        User user = User
                .builder()
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .username(payload.getUsername())
                .email(payload.getEmail())
                .password(payload.getPassword())
                .build();
        try {
            userService.registerUser(user);
        } catch (NotFoundException | AlreadyExistsException | StandardRoleNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true,"User registered successfully"));
    }
}
