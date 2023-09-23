//package com.example.denttech.service.impl;
//
//import com.example.denttech.config.JwtService;
//import com.example.denttech.dto.CompanyDTO;
//import com.example.denttech.dto.AuthenticationResponse;
//import com.example.denttech.dto.LoginRequest;
//import com.example.denttech.dto.RegisterRequest;
//import com.example.denttech.model.User;
//import com.example.denttech.repository.UserRepository;
//import com.example.denttech.repository.UserRoleRepository;
//import com.example.denttech.service.AuthenticationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationServiceImpl implements AuthenticationService {
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    @Override
//    public AuthenticationResponse Register(RegisterRequest request) {
//        var user = User.builder()
//                       .firstName(request.getFirstname())
//                       .lastName(request.getLastname())
//                       .email(request.getEmail())
//                       .password(passwordEncoder.encode(request.getPassword()))
//                       .userRole(userRoleRepository.findByName("user")
//                                                   .orElseThrow())
//                       .build();
//        userRepository.save(user);
//        String jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                                     .accessToken(jwtToken)
//                                     .build();
//    }
//
//    @Override
//    public AuthenticationResponse Login(LoginRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = userRepository.findByEmail(request.getEmail())
//                                 .orElseThrow();
//        String jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                                     .accessToken(jwtToken)
//                                     .build();
//
//    }
//}
