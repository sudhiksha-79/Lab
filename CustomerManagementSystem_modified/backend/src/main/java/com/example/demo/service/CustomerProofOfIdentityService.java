package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CustomerProofOfIdentity;

public interface CustomerProofOfIdentityService {

    CustomerProofOfIdentity addProof(CustomerProofOfIdentity proof);

    List<CustomerProofOfIdentity> getAllProofs();

    CustomerProofOfIdentity getProofById(Long id);

    CustomerProofOfIdentity updateProof(Long id, CustomerProofOfIdentity proof);

    void deleteProof(Long id);
}
