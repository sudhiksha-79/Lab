package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerContactDetailsRequestDTO;
import com.example.demo.dto.CustomerContactDetailsResponseDTO;
import com.example.demo.entity.CustomerContactDetails;
import com.example.demo.entity.CustomerDetails;
import com.example.demo.service.CustomerContactDetailsService;

@RestController
@RequestMapping("/customer-contact-details")
public class CustomerContactDetailsController {

    @Autowired
    private CustomerContactDetailsService service;

    @PostMapping
    public ResponseEntity<CustomerContactDetailsResponseDTO> createContact(@RequestBody CustomerContactDetailsRequestDTO dto) {
        CustomerContactDetails contact = mapRequestToEntity(dto);
        CustomerContactDetails saved = service.addCustomerContact(contact);
        return ResponseEntity.status(201).body(mapEntityToResponse(saved));
    }

    @GetMapping
    public List<CustomerContactDetailsResponseDTO> getAllContacts() {
        return service.getAllContacts().stream().map(this::mapEntityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerContactDetailsResponseDTO> getContactById(@PathVariable Long id) {
        CustomerContactDetails contact = service.getContactById(id);
        if (contact == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(contact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerContactDetailsResponseDTO> updateContact(@PathVariable Long id,
                                                           @RequestBody CustomerContactDetailsRequestDTO dto) {
        CustomerContactDetails contact = mapRequestToEntity(dto);
        CustomerContactDetails updated = service.updateContact(id, contact);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerContactDetails mapRequestToEntity(CustomerContactDetailsRequestDTO dto) {
        CustomerContactDetails contact = new CustomerContactDetails();
        CustomerDetails customer = new CustomerDetails();
        customer.setId(dto.getCustomerId());
        contact.setCustomerIdentification(customer);
        contact.setCustomerContactDetails(dto.getCustomerContactDetails());
        contact.setStartDate(dto.getStartDate());
        contact.setEndDate(dto.getEndDate());
        contact.setUserID(dto.getUserID());
        contact.setWorkstationID(dto.getWorkstationID());
        contact.setProgramID(dto.getProgramID());
        contact.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        contact.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        contact.setUuid(dto.getUuid());
        return contact;
    }

    private CustomerContactDetailsResponseDTO mapEntityToResponse(CustomerContactDetails contact) {
        CustomerContactDetailsResponseDTO dto = new CustomerContactDetailsResponseDTO();
        dto.setCustomerContactID(contact.getCustomerContactID());
        dto.setCustomerId(contact.getCustomerIdentification() != null ? contact.getCustomerIdentification().getId() : null);
        dto.setCustomerContactDetails(contact.getCustomerContactDetails());
        dto.setStartDate(contact.getStartDate());
        dto.setEndDate(contact.getEndDate());
        dto.setCrudValue(contact.getCrudValue());
        dto.setUserID(contact.getUserID());
        dto.setWorkstationID(contact.getWorkstationID());
        dto.setProgramID(contact.getProgramID());
        dto.setUuid(contact.getUuid());
        return dto;
    }
}
