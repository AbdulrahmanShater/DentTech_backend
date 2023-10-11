package com.example.denttech.service;

import com.example.denttech.dto.request.AuthenticationRequest;
import com.example.denttech.dto.response.AuthenticationResponse;
import com.example.denttech.dto.request.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

//    void saveUserToken(User user, String jwtToken);

//    void revokeAllUserTokens(User user);

    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

}
