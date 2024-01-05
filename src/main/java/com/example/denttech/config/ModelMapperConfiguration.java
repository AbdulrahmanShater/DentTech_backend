package com.example.denttech.config;

import com.example.denttech.dto.response.PaymentResponseDTO;
import com.example.denttech.model.Payment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Payment, PaymentResponseDTO> typeMap = modelMapper.createTypeMap(Payment.class, PaymentResponseDTO.class);
        typeMap.addMappings(mapper -> {
            mapper.map(payment -> payment.getPaymentMode()
                                         .getName(), PaymentResponseDTO::setPaymentMode);
        });
//        TypeMap<InvoiceItem, InvoiceItemResponseDTO> typeMap2 = modelMapper.createTypeMap(InvoiceItem.class, InvoiceItemResponseDTO.class);
//        typeMap2.addMappings(mapper -> {
//            mapper.map(invoiceItem -> invoiceItem.getItem(), InvoiceItemResponseDTO::setItem);
//        });
//        TypeMap<Invoice, InvoiceResponseDTO> typeMap1 = modelMapper.createTypeMap(Invoice.class, InvoiceResponseDTO.class);
//        typeMap1.addMappings(mapper -> {
//            mapper.map(invoice -> invoice.getInvoiceItems(), InvoiceResponseDTO::setInvoiceItems);
//        });

        return modelMapper;
    }
}
