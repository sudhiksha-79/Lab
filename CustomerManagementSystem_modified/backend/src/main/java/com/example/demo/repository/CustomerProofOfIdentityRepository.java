package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerProofOfIdentity;

public interface CustomerProofOfIdentityRepository extends JpaRepository<CustomerProofOfIdentity, Long> {
    List<CustomerProofOfIdentity> findAllByCrudValueNot(Character crudValue);
    Optional<CustomerProofOfIdentity> findByIdAndCrudValueNot(Long id, Character crudValue);
}
