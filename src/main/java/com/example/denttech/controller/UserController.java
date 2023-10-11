package com.example.denttech.controller;

import com.example.denttech.config.JwtService;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.UserResponseDTO;
import com.example.denttech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;
    final JwtService jwtService;

    @PostMapping
    public ResponseEntity<DataResponse<UserResponseDTO>> createUser(@RequestHeader("Authorization") String jwtToken, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new DataResponse<>("success", "Customer created successfully.", userService.save(userRequestDTO, username)));
    }

    @PutMapping
    public ResponseEntity<DataResponse<List<UserResponseDTO>>> getCompanies(@RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Customers retrieved successfully.", userService.getUsers(username)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponse<UserResponseDTO>> getCustomer(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Customer retrieved successfully.", userService.getUserById(id, username)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<UserResponseDTO>> editCompany(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company edited successfully.", userService.editUser(userRequestDTO, username, id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        userService.deleteUser(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);

    }

}
