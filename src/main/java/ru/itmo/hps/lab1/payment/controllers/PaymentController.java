package ru.itmo.hps.lab1.payment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hps.lab1.payment.services.PaymentService;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
//        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }
}
