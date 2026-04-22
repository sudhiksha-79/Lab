package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerAddressRequestDTO;
import com.example.demo.dto.CustomerAddressResponseDTO;
import com.example.demo.entity.CustomerAddress;
import com.example.demo.service.CustomerAddressService;

@RestController
@RequestMapping("/customer-address")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService service;

    @PostMapping
    public ResponseEntity<CustomerAddressResponseDTO> create(@RequestBody CustomerAddressRequestDTO dto) {
        CustomerAddress address = mapRequestToEntity(dto);
        CustomerAddress saved = service.addAddress(address);
        return ResponseEntity.status(201).body(mapEntityToResponse(saved));
    }

    @GetMapping
    public List<CustomerAddressResponseDTO> getAll() {
        return service.getAllAddresses()
                      .stream()
                      .map(this::mapEntityToResponse)
                      .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddressResponseDTO> getById(@PathVariable Long id) {
        CustomerAddress address = service.getAddressById(id);
        if (address == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddressResponseDTO> update(@PathVariable Long id,
                                             @RequestBody CustomerAddressRequestDTO dto) {
        CustomerAddress address = mapRequestToEntity(dto);
        CustomerAddress updated = service.updateAddress(id, address);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapEntityToResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerAddress mapRequestToEntity(CustomerAddressRequestDTO dto) {
        CustomerAddress address = new CustomerAddress();
        address.setCustomerIdentifier(dto.getCustomerIdentifier());
        address.setCustomerNameComponentType(dto.getCustomerNameComponentType());
        address.setCustomerNameComponentValue(dto.getCustomerNameComponentValue());
        address.setEffectiveDate(dto.getEffectiveDate());
        address.setUserID(dto.getUserID());
        address.setWorkstationID(dto.getWorkstationID());
        address.setProgramID(dto.getProgramID());
        address.setAcceptanceTimestamp(dto.getAcceptanceTimestamp());
        address.setAcceptanceTimestampUTCoffset(dto.getAcceptanceTimestampUTCoffset());
        address.setUuid(dto.getUuid());
        return address;
    }

    private CustomerAddressResponseDTO mapEntityToResponse(CustomerAddress address) {
        CustomerAddressResponseDTO dto = new CustomerAddressResponseDTO();
        dto.setId(address.getId());
        dto.setCustomerIdentifier(address.getCustomerIdentifier());
        dto.setCustomerNameComponentType(address.getCustomerNameComponentType());
        dto.setCustomerNameComponentValue(address.getCustomerNameComponentValue());
        dto.setEffectiveDate(address.getEffectiveDate());
        dto.setCrudValue(address.getCrudValue());
        dto.setUserID(address.getUserID());
        dto.setWorkstationID(address.getWorkstationID());
        dto.setProgramID(address.getProgramID());
        dto.setUuid(address.getUuid());
        return dto;
    }
}
