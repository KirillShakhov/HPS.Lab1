package ru.itmo.hps.lab1.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itmo.hps.lab1.core.entity.User;
import ru.itmo.hps.lab1.core.exeptions.AlreadyExistsException;
import ru.itmo.hps.lab1.core.exeptions.BadRequestException;
import ru.itmo.hps.lab1.core.exeptions.NotFoundException;
import ru.itmo.hps.lab1.core.exeptions.StandardRoleNotFoundException;
import ru.itmo.hps.lab1.core.payload.ApiResponse;
import ru.itmo.hps.lab1.core.payload.JwtAuthenticationResponse;
import ru.itmo.hps.lab1.core.payload.LoginRequest;
import ru.itmo.hps.lab1.core.payload.SignUpRequest;
import ru.itmo.hps.lab1.core.services.UserDataService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserDataService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
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
