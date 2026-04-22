package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerClassificationRequestDTO;
import com.example.demo.dto.CustomerClassificationResponseDTO;
import com.example.demo.entity.CustomerClassification;
import com.example.demo.service.CustomerClassificationService;

@RestController
@RequestMapping("/customer-classification")
public class CustomerClassificationController {

    @Autowired
    private CustomerClassificationService service;

    @PostMapping
    public ResponseEntity<CustomerClassificationResponseDTO> create(@RequestBody CustomerClassificationRequestDTO dto) {
        CustomerClassification entity = map(dto);
        return ResponseEntity.status(201).body(response(service.add(entity)));
    }

    @GetMapping
    public List<CustomerClassificationResponseDTO> getAll() {
        return service.getAll().stream().map(this::response).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerClassificationResponseDTO> get(@PathVariable Long id) {
        CustomerClassification entity = service.getById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerClassificationResponseDTO> update(@PathVariable Long id,
            @RequestBody CustomerClassificationRequestDTO dto) {
        CustomerClassification updated = service.update(id, map(dto));
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerClassification map(CustomerClassificationRequestDTO dto) {
        CustomerClassification entity = new CustomerClassification();
        entity.setCustomerClassificationType(dto.getCustomerClassificationType());
        entity.setCustomerClassificationTypeValue(dto.getCustomerClassificationTypeValue());
        entity.setEffectiveDate(dto.getEffectiveDate());
        entity.setUserID(dto.getUserID());
        entity.setWorkstationID(dto.getWorkstationID());
        entity.setProgramID(dto.getProgramID());
        entity.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        entity.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        entity.setUuid(dto.getUuid());
        return entity;
    }

    private CustomerClassificationResponseDTO response(CustomerClassification entity) {
        CustomerClassificationResponseDTO dto = new CustomerClassificationResponseDTO();
        dto.setId(entity.getId());
        dto.setCustomerClassificationType(entity.getCustomerClassificationType());
        dto.setCustomerClassificationTypeValue(entity.getCustomerClassificationTypeValue());
        dto.setEffectiveDate(entity.getEffectiveDate());
        dto.setCrudValue(entity.getCrudValue());
        dto.setUserID(entity.getUserID());
        dto.setWorkstationID(entity.getWorkstationID());
        dto.setProgramID(entity.getProgramID());
        dto.setUuid(entity.getUuid());
        return dto;
    }
}
