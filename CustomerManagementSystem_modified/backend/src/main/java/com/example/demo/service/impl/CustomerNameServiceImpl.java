package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerName;
import com.example.demo.repository.CustomerNameRepository;
import com.example.demo.service.CustomerNameService;

@Service
public class CustomerNameServiceImpl implements CustomerNameService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerNameRepository repo;

    @Override
    public CustomerName add(CustomerName name) {
        name.setCrudValue(CRUD_CREATE);
        return repo.save(name);
    }

    @Override
    public List<CustomerName> getAll() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerName getById(Long id) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerName update(Long id, CustomerName name) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).map(existing -> {
            existing.setCustomerIdentifier(name.getCustomerIdentifier());
            existing.setCustomerNameComponentType(name.getCustomerNameComponentType());
            existing.setCustomerNameComponentValue(name.getCustomerNameComponentValue());
            existing.setEffectiveDate(name.getEffectiveDate());
            existing.setUserID(name.getUserID());
            existing.setWorkstationID(name.getWorkstationID());
            existing.setProgramID(name.getProgramID());
            existing.setAcceptanceTimestamp(name.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(name.getAcceptanceTimestampUTCoffset());
            existing.setUuid(name.getUuid());
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
