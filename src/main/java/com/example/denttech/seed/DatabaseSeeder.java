package com.example.denttech.seed;

import com.example.denttech.model.*;
import com.example.denttech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public void run(String... args) {
        //User Role
        UserRole userRoleManager = new UserRole(null, "manager", null);
        UserRole userRoleCompany = new UserRole(null, "company", null);
        UserRole userRoleDoctor = new UserRole(null, "doctor", null);
        userRoleRepository.saveAll(List.of(userRoleManager, userRoleCompany, userRoleDoctor));

        //Base Company
        Company laboratory = new Company(1L, "Laboratory", "+971503131842", "pobox", "info@Denttech.com", "654654894153121864", "Abu Dhabi Alkhalduyah", false, 1L, null, null, null);
        companyRepository.save(laboratory);

        // Clinic
        Company clinic = new Company(2L, "clinic", "+971503131842", "pobox", "info@Denttech.com", "654654894153121864", "Abu Dhabi Alkhalduyah", false, 1L, null, laboratory, null);
        companyRepository.save(clinic);

        // Vendor
        Company vendor = new Company(3L, "vendor", "+971503131842", "pobox", "info@Denttech.com", "654654894153121864", "Abu Dhabi Alkhalduyah", true, 1L, null, laboratory, null);
        companyRepository.save(vendor);

        //account manager for the base company
        Account manager = new Account(1L, "fname", "lName", "qwe@gmail.com", "12312312", laboratory, null, userRoleManager);
        accountRepository.save(manager);

        //account for company for sub company
        Account clinicManager = new Account(2L, "account", "lName", "qwe@gmail.com", "12312312", clinic, null, userRoleCompany);
        accountRepository.save(clinicManager);

        //account for company for sub company
        Account clinicDoctor = new Account(3L, "Doctor", "lName", "qwe@gmail.com", "12312312", clinic, null, userRoleDoctor);
        accountRepository.save(clinicDoctor);

        //account for company for sub company
        Account vendorManager = new Account(4L, "vendor", "Manger", "qwe@gmail.com", "12312312", vendor, null, userRoleCompany);
        accountRepository.save(vendorManager);

        //Item
        Item item = new Item(1L, "Item1", "Description", 100, 200, 300, 400, null);
        itemRepository.save(item);

        //Sell Invoice
        invoiceRepository.save(new Invoice(1L, "INV-00001", "patient", "12312", "123123", "asdasd", false, true, clinicDoctor,
                null, List.of(new InvoiceItem(1L, 1, 200, null, item))));

        //buy Invoice
        invoiceRepository.save(new Invoice(2L, "B_INV-00001", "", "", "", "", false, false, vendorManager,
                null, List.of(new InvoiceItem(2L, 4, 200, null, item))));
    }
}