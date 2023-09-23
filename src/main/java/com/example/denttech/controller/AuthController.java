//package com.example.denttech.controller;
//
//import com.example.denttech.dto.AuthenticationRequest;
//import com.example.denttech.dto.LoginRequest;
//import com.example.denttech.dto.AuthenticationResponse;
//import com.example.denttech.dto.RegisterRequest;
//import com.example.denttech.service.AuthenticationService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthenticationService authenticationService;
//    @PostMapping("/auth/login")
//    public AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticate(request))
//                             .getBody();
//    }
//    @PostMapping("/auth/register")
//    public AuthenticationResponse register(@RequestBody @Valid RegisterRequest request) {
//        return ResponseEntity.ok(authenticationService.register(request))
//                             .getBody();
//    }
//}
