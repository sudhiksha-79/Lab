package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerNameRequestDTO;
import com.example.demo.dto.CustomerNameResponseDTO;
import com.example.demo.entity.CustomerName;
import com.example.demo.service.CustomerNameService;

@RestController
@RequestMapping("/customer-name")
public class CustomerNameController {

    @Autowired
    private CustomerNameService service;

    @PostMapping
    public ResponseEntity<CustomerNameResponseDTO> create(@RequestBody CustomerNameRequestDTO dto) {
        CustomerName name = map(dto);
        return ResponseEntity.status(201).body(response(service.add(name)));
    }

    @GetMapping
    public List<CustomerNameResponseDTO> getAll() {
        return service.getAll().stream().map(this::response).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerNameResponseDTO> get(@PathVariable Long id) {
        CustomerName name = service.getById(id);
        if (name == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerNameResponseDTO> update(@PathVariable Long id,
                                         @RequestBody CustomerNameRequestDTO dto) {
        CustomerName updated = service.update(id, map(dto));
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerName map(CustomerNameRequestDTO dto) {
        CustomerName n = new CustomerName();
        n.setCustomerIdentifier(dto.getCustomerIdentifier());
        n.setCustomerNameComponentType(dto.getCustomerNameComponentType());
        n.setCustomerNameComponentValue(dto.getCustomerNameComponentValue());
        n.setEffectiveDate(dto.getEffectiveDate());
        n.setUserID(dto.getUserID());
        n.setWorkstationID(dto.getWorkstationID());
        n.setProgramID(dto.getProgramID());
        n.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        n.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        n.setUuid(dto.getUuid());
        return n;
    }

    private CustomerNameResponseDTO response(CustomerName n) {
        CustomerNameResponseDTO dto = new CustomerNameResponseDTO();
        dto.setId(n.getId());
        dto.setCustomerIdentifier(n.getCustomerIdentifier());
        dto.setCustomerNameComponentType(n.getCustomerNameComponentType());
        dto.setCustomerNameComponentValue(n.getCustomerNameComponentValue());
        dto.setEffectiveDate(n.getEffectiveDate());
        dto.setCrudValue(n.getCrudValue());
        dto.setUserID(n.getUserID());
        dto.setWorkstationID(n.getWorkstationID());
        dto.setProgramID(n.getProgramID());
        dto.setUuid(n.getUuid());
        return dto;
    }
}
