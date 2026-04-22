package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerIdentification;
import com.example.demo.repository.CustomerIdentificationRepository;
import com.example.demo.service.CustomerIdentificationService;

@Service
public class CustomerIdentificationServiceImpl implements CustomerIdentificationService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerIdentificationRepository repo;

    @Override
    public CustomerIdentification addIdentification(CustomerIdentification identification) {
        identification.setCrudValue(CRUD_CREATE);
        return repo.save(identification);
    }

    @Override
    public List<CustomerIdentification> getAllIdentifications() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerIdentification getIdentificationById(Long id) {
        return repo.findByCustomerIdentificationIDAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerIdentification updateIdentification(Long id, CustomerIdentification identification) {
        Optional<CustomerIdentification> existingOpt = repo.findByCustomerIdentificationIDAndCrudValueNot(id, CRUD_DELETE);
        if (existingOpt.isPresent()) {
            CustomerIdentification existing = existingOpt.get();
            existing.setCustomerIdentificationType(identification.getCustomerIdentificationType());
            existing.setCustomerIdentificationItem(identification.getCustomerIdentificationItem());
            existing.setEffectiveDate(identification.getEffectiveDate());
            existing.setUserID(identification.getUserID());
            existing.setWorkstationID(identification.getWorkstationID());
            existing.setProgramID(identification.getProgramID());
            existing.setAcceptanceTimestamp(identification.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(identification.getAcceptanceTimestampUTCoffset());
            existing.setUuid(identification.getUuid());
            existing.setCrudValue(CRUD_UPDATE);  // audit: mark as updated
            return repo.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteIdentification(Long id) {
        repo.findByCustomerIdentificationIDAndCrudValueNot(id, CRUD_DELETE).ifPresent(existing -> {
            existing.setCrudValue(CRUD_DELETE);
            repo.save(existing);
        });
    }
}
