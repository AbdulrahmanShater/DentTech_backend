package com.example.denttech.seed;

import com.example.denttech.model.*;
import com.example.denttech.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    private final ItemRepository itemRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PaymentModeRepository paymentModeRepository;
    private final PaymentRepository paymentRepository;


    @Override
    public void run(String... args) {
        //User Role
        UserRole userRoleManager = UserRole.builder()
                                           .name("manager")
                                           .build();
        UserRole userRoleCompany = UserRole.builder()
                                           .name("company")
                                           .build();
        UserRole userRoleDoctor = UserRole.builder()
                                          .name("doctor")
                                          .build();
        userRoleRepository.saveAll(List.of(userRoleManager, userRoleCompany, userRoleDoctor));
        //Payment Mode
        PaymentMode cash = PaymentMode.builder()
                                      .name("cash")
                                      .build();
        PaymentMode bank = PaymentMode.builder()
                                      .name("cash")
                                      .build();
        paymentModeRepository.saveAll(List.of(cash, bank));
        //Base Company
        Company laboratory = Company.builder()
                                    .name("Laboratory")
                                    .tel("+971503131842")
                                    .email("info@Denttech.com")
                                    .TRN("214587963251453")
                                    .address("Abu Dhabi Alkhalduyah")
                                    .vendor(false)
                                    .price_stage(1)
                                    .build();

        companyRepository.save(laboratory);

        // Clinic
        Company clinic = Company.builder()
                                .name("clinic")
                                .tel("+971503131842")
                                .email("info@Denttech.com")
                                .TRN("214587963251453")
                                .address("Abu Dhabi Alkhalduyah")
                                .vendor(false)
                                .price_stage(1)
                                .parent(laboratory)
                                .build();
        companyRepository.save(clinic);

        // Vendor
        Company vendor = Company.builder()
                                .name("vendor")
                                .tel("+971503131842")
                                .email("info@Denttech.com")
                                .TRN("214587963251453")
                                .address("Abu Dhabi Alkhalduyah")
                                .vendor(true)
                                .price_stage(1)
                                .parent(laboratory)
                                .build();
        companyRepository.save(vendor);

        //account manager for the base company
        User manager = User.builder()
                           .firstName("fname")
                           .lastName("la=name")
                           .email("qwe@gmail.com")
                           .password("1231231231")
                           .company(laboratory)
                           .userRole(userRoleManager)
                           .tel("+971503131842")
                           .build();
        userRepository.save(manager);

        //account for company for sub company
        User clinicManager =

                User.builder()
                    .firstName("account")
                    .lastName("la=name")
                    .email("qwe@gmail.com")
                    .password("1231231231")
                    .company(clinic)
                    .userRole(userRoleCompany)
                    .tel("+971503131842")
                    .build();
        userRepository.save(clinicManager);

        //account for company for sub company
        User clinicDoctor =
                User.builder()
                    .firstName("Doctor")
                    .lastName("la=name")
                    .email("qwe@gmail.com")
                    .password("1231231231")
                    .company(clinic)
                    .userRole(userRoleDoctor)
                        .tel("+971503131842")
                    .build();

        userRepository.save(clinicDoctor);

        //account for company for sub company
        User vendorManager =

                User.builder()
                    .firstName("vendor")
                    .lastName("Manger")
                    .email("qwe@gmail.com")
                    .password("1231231231")
                    .company(vendor)
                    .userRole(userRoleCompany)
                        .tel("+971503134842")
                    .build();

        userRepository.save(vendorManager);

        //Item
        Item item = Item.builder()
                        .name("Item1")
                        .description("desc")
                        .price1(100)
                        .price2(200)
                        .price3(300)
                        .price4(400)
                .company(laboratory)
                        .build();


        itemRepository.save(item);

        //Sell Invoice
        Invoice sell = Invoice.builder()
                              .invoiceNumber("INV-00001")
                              .patientName("patient")
                              .fileNumber("123")
                              .jobOrder("123")
                              .invoiceDate(LocalDate.now())
                              .sell(true)
                              .paid(true)
                              .user(clinicDoctor)
                              .invoiceItems(List.of(InvoiceItem.builder().
                                                               quantity(1)
                                                               .unitPrice(200)
                                                               .item(item)
                                                               .build())
                              )
                              .total(200)
                              .build();
        invoiceRepository.save(sell);

        //buy Invoice
        Invoice buy = Invoice.builder()
                             .invoiceNumber("B_INV-00001")
                             .sell(false)
                             .paid(true)
                             .user(vendorManager)
                             .invoiceDate(LocalDate.now())

                             .invoiceItems(List.of(InvoiceItem.builder().
                                                              quantity(4)
                                                              .unitPrice(100)
                                                              .item(item)
                                                              .build())
                             )
                             .total(400)
                             .build();
        invoiceRepository.save(buy);

        //payment
        Payment payment = Payment.builder()
                                 .paymentMode(cash)
                                 .paymentNumber("Pay_123")
                                 .paymentDate(LocalDate.now())
                                 .amount(5000)
                                 .build();
        InvoicePayment invoicePayment = InvoicePayment.builder()
                                                      .invoice(sell)
                                                      .payment(payment)
                                                      .amount(5000)
                                                      .build();
        InvoicePayment invoicePayment1 = InvoicePayment.builder()
                                                       .invoice(buy)
                                                       .payment(payment)
                                                       .amount(2000)
                                                       .build();
        payment.setInvoicePayments(List.of(invoicePayment, invoicePayment1));
        paymentRepository.save(payment);


    }
}