package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CustomerClassification;

public interface CustomerClassificationRepository extends JpaRepository<CustomerClassification, Long> {
    List<CustomerClassification> findAllByCrudValueNot(Character crudValue);
    Optional<CustomerClassification> findByIdAndCrudValueNot(Long id, Character crudValue);
}
