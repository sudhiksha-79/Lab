package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CustomerIdentification;

public interface CustomerIdentificationRepository extends JpaRepository<CustomerIdentification, Long> {
    List<CustomerIdentification> findAllByCrudValueNot(Character crudValue);
    Optional<CustomerIdentification> findByCustomerIdentificationIDAndCrudValueNot(Long id, Character crudValue);
}
