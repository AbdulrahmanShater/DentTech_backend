package com.example.denttech.service;

import com.example.denttech.dto.request.ItemRequestDTO;
import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.ItemResponseDTO;
import com.example.denttech.dto.response.UserResponseDTO;

import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> getItems(String username);

    ItemResponseDTO getItemById(Long id, String username);

    ItemResponseDTO save(ItemRequestDTO itemRequestDTO, String username);

    void deleteItem(Long id, String username);

    ItemResponseDTO editItem(ItemRequestDTO itemRequestDTO, String username, Long id);
}
