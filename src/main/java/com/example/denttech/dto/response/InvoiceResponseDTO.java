package com.example.denttech.dto.response;

import com.example.denttech.model.InvoiceItem;
import com.example.denttech.model.InvoicePayment;
import com.example.denttech.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.List;


@Data

public class InvoiceResponseDTO {
    private Long id;
    private String invoiceNumber;
    private String patientName;
    private String fileNumber;
    private String jobOrder;
    private String Shade;
    private boolean paid;
    private boolean sell;
    private double discount;
    private double total;

//    private List<InvoicePayment> invoicePayments;
//    private List<InvoiceItem> invoiceItems;
}
