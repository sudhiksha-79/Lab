package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerProofOfIdentityRequestDTO;
import com.example.demo.dto.CustomerProofOfIdentityResponseDTO;
import com.example.demo.entity.CustomerProofOfIdentity;
import com.example.demo.service.CustomerProofOfIdentityService;

@RestController
@RequestMapping("/customer-proof-of-identity")
public class CustomerProofOfIdentityController {

    @Autowired
    private CustomerProofOfIdentityService service;

    @PostMapping
    public ResponseEntity<CustomerProofOfIdentityResponseDTO> create(@RequestBody CustomerProofOfIdentityRequestDTO dto) {
        CustomerProofOfIdentity proof = mapRequestToEntity(dto);
        CustomerProofOfIdentity saved = service.addProof(proof);
        return ResponseEntity.status(201).body(mapEntityToResponse(saved));
    }

    @GetMapping
    public List<CustomerProofOfIdentityResponseDTO> getAll() {
        return service.getAllProofs().stream().map(this::mapEntityToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProofOfIdentityResponseDTO> getById(@PathVariable Long id) {
        CustomerProofOfIdentity proof = service.getProofById(id);
        if (proof == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(proof));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerProofOfIdentityResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody CustomerProofOfIdentityRequestDTO dto) {
        CustomerProofOfIdentity proof = mapRequestToEntity(dto);
        CustomerProofOfIdentity updated = service.updateProof(id, proof);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProof(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerProofOfIdentity mapRequestToEntity(CustomerProofOfIdentityRequestDTO dto) {
        CustomerProofOfIdentity proof = new CustomerProofOfIdentity();
        proof.setCustomerIdentifier(dto.getCustomerIdentifier());
        proof.setCustomerClassificationTypeValue(dto.getCustomerClassificationTypeValue());
        proof.setStartDate(dto.getStartDate());
        proof.setEndDate(dto.getEndDate());
        proof.setUserID(dto.getUserID());
        proof.setWorkstationID(dto.getWorkstationID());
        proof.setProgramID(dto.getProgramID());
        proof.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        proof.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        proof.setUuid(dto.getUuid());
        return proof;
    }

    private CustomerProofOfIdentityResponseDTO mapEntityToResponse(CustomerProofOfIdentity proof) {
        CustomerProofOfIdentityResponseDTO dto = new CustomerProofOfIdentityResponseDTO();
        dto.setId(proof.getId());
        dto.setCustomerIdentifier(proof.getCustomerIdentifier());
        dto.setCustomerClassificationTypeValue(proof.getCustomerClassificationTypeValue());
        dto.setStartDate(proof.getStartDate());
        dto.setEndDate(proof.getEndDate());
        dto.setCrudValue(proof.getCrudValue());
        dto.setUserID(proof.getUserID());
        dto.setWorkstationID(proof.getWorkstationID());
        dto.setProgramID(proof.getProgramID());
        dto.setUuid(proof.getUuid());
        return dto;
    }
}
