package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerAddress;
import com.example.demo.repository.CustomerAddressRepository;
import com.example.demo.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerAddressRepository repo;

    @Override
    public CustomerAddress addAddress(CustomerAddress address) {
        address.setCrudValue(CRUD_CREATE);
        return repo.save(address);
    }

    @Override
    public List<CustomerAddress> getAllAddresses() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerAddress getAddressById(Long id) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerAddress updateAddress(Long id, CustomerAddress address) {
        Optional<CustomerAddress> existingOpt = repo.findByIdAndCrudValueNot(id, CRUD_DELETE);
        if (existingOpt.isPresent()) {
            CustomerAddress existing = existingOpt.get();
            existing.setCustomerIdentifier(address.getCustomerIdentifier());
            existing.setCustomerNameComponentType(address.getCustomerNameComponentType());
            existing.setCustomerNameComponentValue(address.getCustomerNameComponentValue());
            existing.setEffectiveDate(address.getEffectiveDate());
            existing.setUserID(address.getUserID());
            existing.setWorkstationID(address.getWorkstationID());
            existing.setProgramID(address.getProgramID());
            existing.setAcceptanceTimestamp(address.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(address.getAcceptanceTimestampUTCoffset());
            existing.setUuid(address.getUuid());
            existing.setCrudValue(CRUD_UPDATE);  // audit: mark as updated
            return repo.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAddress(Long id) {
        repo.findByIdAndCrudValueNot(id, CRUD_DELETE).ifPresent(existing -> {
            existing.setCrudValue(CRUD_DELETE);
            repo.save(existing);
        });
    }
}
