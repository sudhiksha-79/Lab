package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    List<CustomerAddress> findAllByCrudValueNot(Character crudValue);
    Optional<CustomerAddress> findByIdAndCrudValueNot(Long id, Character crudValue);
}
