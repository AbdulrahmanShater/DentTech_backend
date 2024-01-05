package com.example.denttech.service;

import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getUsers(String username);

    UserResponseDTO getUserById(Long id, String username);

    UserResponseDTO save(UserRequestDTO userRequestDTO, String username);

    void deleteUser(Long id, String username);

    UserResponseDTO editUser(UserRequestDTO userRequestDTO, String username, Long id);

    List<UserResponseDTO> getBuyers(String username);

    List<UserResponseDTO> getVendors(String username);
}
