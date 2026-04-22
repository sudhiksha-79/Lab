package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerProofOfIdentity;
import com.example.demo.repository.CustomerProofOfIdentityRepository;
import com.example.demo.service.CustomerProofOfIdentityService;

@Service
public class CustomerProofOfIdentityServiceImpl implements CustomerProofOfIdentityService {

    private static final char CRUD_CREATE = 'C';
    private static final char CRUD_UPDATE = 'U';
    private static final char CRUD_DELETE = 'D';

    @Autowired
    private CustomerProofOfIdentityRepository repo;

    @Override
    public CustomerProofOfIdentity addProof(CustomerProofOfIdentity proof) {
        proof.setCrudValue(CRUD_CREATE);
        return repo.save(proof);
    }

    @Override
    public List<CustomerProofOfIdentity> getAllProofs() {
        return repo.findAllByCrudValueNot(CRUD_DELETE);
    }

    @Override
    public CustomerProofOfIdentity getProofById(Long id) {
        return repo.findByIdAndCrudValueNot(id, CRUD_DELETE).orElse(null);
    }

    @Override
    public CustomerProofOfIdentity updateProof(Long id, CustomerProofOfIdentity proof) {
        Optional<CustomerProofOfIdentity> existingOpt = repo.findByIdAndCrudValueNot(id, CRUD_DELETE);
        if (existingOpt.isPresent()) {
            CustomerProofOfIdentity existing = existingOpt.get();
            existing.setCustomerIdentifier(proof.getCustomerIdentifier());
            existing.setCustomerClassificationTypeValue(proof.getCustomerClassificationTypeValue());
            existing.setStartDate(proof.getStartDate());
            existing.setEndDate(proof.getEndDate());
            existing.setUserID(proof.getUserID());
            existing.setWorkstationID(proof.getWorkstationID());
            existing.setProgramID(proof.getProgramID());
            existing.setAcceptanceTimestamp(proof.getAcceptanceTimestamp());
            existing.setAcceptanceTimestampUTCoffset(proof.getAcceptanceTimestampUTCoffset());
            existing.setUuid(proof.getUuid());
            existing.setCrudValue(CRUD_UPDATE);  // audit: mark as updated
            return repo.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProof(Long id) {
        repo.findByIdAndCrudValueNot(id, CRUD_DELETE).ifPresent(existing -> {
            existing.setCrudValue(CRUD_DELETE);
            repo.save(existing);
        });
    }
}
