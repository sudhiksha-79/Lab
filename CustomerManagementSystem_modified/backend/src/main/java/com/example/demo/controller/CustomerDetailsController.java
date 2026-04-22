package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerDetailsRequestDTO;
import com.example.demo.dto.CustomerDetailsResponseDTO;
import com.example.demo.entity.CustomerDetails;
import com.example.demo.service.CustomerDetailsService;

@RestController
@RequestMapping("/customer-details")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService service;

    // CREATE
    @PostMapping
    public ResponseEntity<CustomerDetailsResponseDTO> createCustomer(@RequestBody CustomerDetailsRequestDTO requestDTO) {
        CustomerDetails customer = mapRequestToEntity(requestDTO);
        CustomerDetails savedCustomer = service.addCustomerDetails(customer);
        return ResponseEntity.status(201).body(mapEntityToResponse(savedCustomer));
    }

    // READ ALL
    @GetMapping
    public List<CustomerDetailsResponseDTO> getAllCustomers() {
        return service.getAllCustomers()
                      .stream()
                      .map(this::mapEntityToResponse)
                      .collect(Collectors.toList());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailsResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerDetails customer = service.getCustomerById(id);
        if (customer == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(customer));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDetailsResponseDTO> updateCustomer(@PathVariable Long id,
                                                     @RequestBody CustomerDetailsRequestDTO requestDTO) {
        CustomerDetails customer = mapRequestToEntity(requestDTO);
        CustomerDetails updatedCustomer = service.updateCustomer(id, customer);
        if (updatedCustomer == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(updatedCustomer));
    }

    // DELETE (soft)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerDetails mapRequestToEntity(CustomerDetailsRequestDTO dto) {
        CustomerDetails customer = new CustomerDetails();
        customer.setCustomerType(dto.getCustomerType());
        customer.setCustomerDOB(dto.getCustomerDOB());
        customer.setCustomerStatus(dto.getCustomerStatus());
        customer.setCustomerGender(dto.getCustomerGender());
        customer.setCustomerPreferredLanguage(dto.getCustomerPreferredLanguage());
        customer.setCustomerCountryOrigin(dto.getCustomerCountryOrigin());
        customer.setEffectiveDate(dto.getEffectiveDate());
        customer.setUserID(dto.getUserID());
        customer.setWorkstationID(dto.getWorkstationID());
        customer.setProgramID(dto.getProgramID());
        customer.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        customer.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        customer.setUuid(dto.getUuid());
        return customer;
    }

    private CustomerDetailsResponseDTO mapEntityToResponse(CustomerDetails customer) {
        CustomerDetailsResponseDTO dto = new CustomerDetailsResponseDTO();
        dto.setCustomerIdentifier(customer.getCustomerIdentifier());
        dto.setCustomerType(customer.getCustomerType());
        dto.setCustomerDOB(customer.getCustomerDOB());
        dto.setCustomerStatus(customer.getCustomerStatus());
        dto.setCustomerGender(customer.getCustomerGender());
        dto.setCustomerPreferredLanguage(customer.getCustomerPreferredLanguage());
        dto.setCustomerCountryOrigin(customer.getCustomerCountryOrigin());
        dto.setCrudValue(customer.getCrudValue());
        dto.setUserID(customer.getUserID());
        dto.setWorkstationID(customer.getWorkstationID());
        dto.setProgramID(customer.getProgramID());
        dto.setHostTimestamp(customer.getHostTimestamp());
        dto.setLocalTimestamp(customer.getLocalTimestamp());
        dto.setUuid(customer.getUuid());
        return dto;
    }
}
