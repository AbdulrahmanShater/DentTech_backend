package com.example.denttech.controller;

import com.example.denttech.config.JwtService;
import com.example.denttech.dto.DataResponse;
import com.example.denttech.dto.request.ItemRequestDTO;
import com.example.denttech.dto.request.UserRequestDTO;
import com.example.denttech.dto.response.ItemResponseDTO;
import com.example.denttech.dto.response.UserResponseDTO;
import com.example.denttech.service.ItemService;
import com.example.denttech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    final ItemService itemService;
    final JwtService jwtService;

    @PostMapping
    public ResponseEntity<DataResponse<ItemResponseDTO>> createUser(@RequestHeader("Authorization") String jwtToken, @RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new DataResponse<>("success", "Customer created successfully.", itemService.save(itemRequestDTO, username)));
    }

    @PutMapping
    public ResponseEntity<DataResponse<List<ItemResponseDTO>>> getCompanies(@RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Customers retrieved successfully.", itemService.getItems(username)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponse<ItemResponseDTO>> getCustomer(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Customer retrieved successfully.", itemService.getItemById(id, username)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ItemResponseDTO>> editCompany(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken, @RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new DataResponse<>("success", "Company edited successfully.", itemService.editItem(itemRequestDTO, username, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id, @RequestHeader("Authorization") String jwtToken) {
        String username = jwtService.extractUsername(jwtToken.substring(7));

        itemService.deleteItem(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(null);

    }

}
