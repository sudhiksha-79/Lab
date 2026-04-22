package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {
    List<CustomerDetails> findAllByCrudValueNot(Character crudValue);
    Optional<CustomerDetails> findByIdAndCrudValueNot(Long id, Character crudValue);
}
