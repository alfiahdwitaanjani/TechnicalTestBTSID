package com.technicaltes.tesbts.id.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

import com.technicaltes.tesbts.id.dto.AuthRequest;
import com.technicaltes.tesbts.id.dto.AuthResponse;
import com.technicaltes.tesbts.id.dto.RegisterRequest;
import com.technicaltes.tesbts.id.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

        String response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}
