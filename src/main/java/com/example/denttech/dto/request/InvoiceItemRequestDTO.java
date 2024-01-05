package com.example.denttech.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class InvoiceItemRequestDTO {
    @NotNull(message = "quantity is required")
    private Integer quantity;
    @NotNull(message = "unitPrice is required")
    private Double unitPrice;
    @NotNull(message = "item is required")
    private Long item;
}
