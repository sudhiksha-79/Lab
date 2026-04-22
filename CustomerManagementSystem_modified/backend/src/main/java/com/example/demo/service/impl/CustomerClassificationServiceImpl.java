package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerClassification;
import com.example.demo.repository.CustomerClassificationRepository;
import com.example.demo.service.CustomerClassificationService;

@Service
public class CustomerClassificationServiceImpl implements CustomerClassificationService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerClassificationRepository repo;

    @Override
    public CustomerClassification add(CustomerClassification entity) {
        entity.setCrudValue(CRUD_CREATE);
        return repo.save(entity);
    }

    @Override
    public List<CustomerClassification> getAll() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerClassification getById(Long id) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerClassification update(Long id, CustomerClassification entity) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).map(existing -> {
            existing.setCustomerClassificationType(entity.getCustomerClassificationType());
            existing.setCustomerClassificationTypeValue(entity.getCustomerClassificationTypeValue());
            existing.setEffectiveDate(entity.getEffectiveDate());
            existing.setUserID(entity.getUserID());
            existing.setWorkstationID(entity.getWorkstationID());
            existing.setProgramID(entity.getProgramID());
            existing.setAcceptanceTimestamp(entity.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(entity.getAcceptanceTimestampUTCoffset());
            existing.setUuid(entity.getUuid());
            existing.setCrudValue(CRUD_UPDATE);  // audit: mark as updated
            return repo.save(existing);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.findByIdAndCrudValueNot(id, CRUD_DELETE).ifPresent(existing -> {
            existing.setCrudValue(CRUD_DELETE);
            repo.save(existing);
        });
    }
}
