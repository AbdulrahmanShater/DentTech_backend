package com.example.denttech.dto.response;

import lombok.Data;

@Data

public class InvoiceItemResponseDTO {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private ItemResponseDTO item;

}
