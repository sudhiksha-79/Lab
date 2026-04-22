package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerContactDetails;
import com.example.demo.repository.CustomerContactDetailsRepository;
import com.example.demo.service.CustomerContactDetailsService;

@Service
public class CustomerContactDetailsServiceImpl implements CustomerContactDetailsService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerContactDetailsRepository repo;

    @Override
    public CustomerContactDetails addCustomerContact(CustomerContactDetails contact) {
        contact.setCrudValue(CRUD_CREATE);
        return repo.save(contact);
    }

    @Override
    public List<CustomerContactDetails> getAllContacts() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerContactDetails getContactById(Long id) {
        return repo.findByCustomerContactIDAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerContactDetails updateContact(Long id, CustomerContactDetails contact) {
        Optional<CustomerContactDetails> existingOpt = repo.findByCustomerContactIDAndCrudValueNot(id, CRUD_DELETE);
        if (existingOpt.isPresent()) {
            CustomerContactDetails existing = existingOpt.get();
            existing.setCustomerIdentification(contact.getCustomerIdentification());
            existing.setCustomerContactDetails(contact.getCustomerContactDetails());
            existing.setStartDate(contact.getStartDate());
            existing.setEndDate(contact.getEndDate());
            existing.setUserID(contact.getUserID());
            existing.setWorkstationID(contact.getWorkstationID());
            existing.setProgramID(contact.getProgramID());
            existing.setAcceptanceTimestamp(contact.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(contact.getAcceptanceTimestampUTCoffset());
            existing.setUuid(contact.getUuid());
            existing.setCrudValue(CRUD_UPDATE);  // audit: mark as updated
            return repo.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteContact(Long id) {
        repo.findByCustomerContactIDAndCrudValueNot(id, CRUD_DELETE).ifPresent(existing -> {
            existing.setCrudValue(CRUD_DELETE);
            repo.save(existing);
        });
    }
}
