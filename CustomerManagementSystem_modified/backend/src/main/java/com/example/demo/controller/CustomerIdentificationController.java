package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerIdentificationRequestDTO;
import com.example.demo.dto.CustomerIdentificationResponseDTO;
import com.example.demo.entity.CustomerIdentification;
import com.example.demo.service.CustomerIdentificationService;

@RestController
@RequestMapping("/customer-identification")
public class CustomerIdentificationController {

    @Autowired
    private CustomerIdentificationService service;

    @PostMapping
    public ResponseEntity<CustomerIdentificationResponseDTO> create(@RequestBody CustomerIdentificationRequestDTO dto) {
        CustomerIdentification identification = mapRequestToEntity(dto);
        CustomerIdentification saved = service.addIdentification(identification);
        return ResponseEntity.status(201).body(mapEntityToResponse(saved));
    }

    @GetMapping
    public List<CustomerIdentificationResponseDTO> getAll() {
        return service.getAllIdentifications().stream().map(this::mapEntityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerIdentificationResponseDTO> getById(@PathVariable Long id) {
        CustomerIdentification identification = service.getIdentificationById(id);
        if (identification == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(identification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerIdentificationResponseDTO> update(@PathVariable Long id,
                                                    @RequestBody CustomerIdentificationRequestDTO dto) {
        CustomerIdentification identification = mapRequestToEntity(dto);
        CustomerIdentification updated = service.updateIdentification(id, identification);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteIdentification(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerIdentification mapRequestToEntity(CustomerIdentificationRequestDTO dto) {
        CustomerIdentification identification = new CustomerIdentification();
        identification.setCustomerIdentificationType(dto.getCustomerIdentificationType());
        identification.setCustomerIdentificationItem(dto.getCustomerIdentificationItem());
        identification.setEffectiveDate(dto.getEffectiveDate());
        identification.setUserID(dto.getUserID());
        identification.setWorkstationID(dto.getWorkstationID());
        identification.setProgramID(dto.getProgramID());
        identification.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        identification.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        identification.setUuid(dto.getUuid());
        return identification;
    }

    private CustomerIdentificationResponseDTO mapEntityToResponse(CustomerIdentification identification) {
        CustomerIdentificationResponseDTO dto = new CustomerIdentificationResponseDTO();
        dto.setCustomerIdentificationID(identification.getCustomerIdentificationID());
        dto.setCustomerIdentificationType(identification.getCustomerIdentificationType());
        dto.setCustomerIdentificationItem(identification.getCustomerIdentificationItem());
        dto.setEffectiveDate(identification.getEffectiveDate());
        dto.setCrudValue(identification.getCrudValue());
        dto.setUserID(identification.getUserID());
        dto.setWorkstationID(identification.getWorkstationID());
        dto.setProgramID(identification.getProgramID());
        dto.setUuid(identification.getUuid());
        return dto;
    }
}
