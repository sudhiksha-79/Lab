package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerDetails;
import com.example.demo.repository.CustomerDetailsRepository;
import com.example.demo.service.CustomerDetailsService;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerDetailsRepository repo;

    @Override
    public CustomerDetails addCustomerDetails(CustomerDetails customerDetails) {
        customerDetails.setCrudValue(CRUD_CREATE);
        return repo.save(customerDetails);
    }

    @Override
    public List<CustomerDetails> getAllCustomers() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerDetails getCustomerById(Long id) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerDetails updateCustomer(Long id, CustomerDetails customerDetails) {
        Optional<CustomerDetails> existingOpt = repo.findByIdAndCrudValueNot(id, CRUD_DELETE);
        if (existingOpt.isPresent()) {
            CustomerDetails existing = existingOpt.get();
            existing.setCustomerType(customerDetails.getCustomerType());
            existing.setCustomerDOB(customerDetails.getCustomerDOB());
            existing.setCustomerStatus(customerDetails.getCustomerStatus());
            existing.setCustomerGender(customerDetails.getCustomerGender());
            existing.setCustomerPreferredLanguage(customerDetails.getCustomerPreferredLanguage());
            existing.setCustomerCountryOrigin(customerDetails.getCustomerCountryOrigin());
            existing.setEffectiveDate(customerDetails.getEffectiveDate());
            existing.setUserID(customerDetails.getUserID());
            existing.setWorkstationID(customerDetails.getWorkstationID());
            existing.setProgramID(customerDetails.getProgramID());
            existing.setAcceptanceTimestamp(customerDetails.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(customerDetails.getAcceptanceTimestampUTCoffset());
            existing.setUuid(customerDetails.getUuid());
            existing.setCrudValue(CRUD_UPDATE);  // audit: mark as updated
            return repo.save(existing);
        } else {
            return null; // not found or already deleted
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        repo.findByIdAndCrudValueNot(id, CRUD_DELETE).ifPresent(existing -> {
            existing.setCrudValue(CRUD_DELETE);  // soft delete
            repo.save(existing);  // triggers @LastModifiedDate update
        });
    }
}
