package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CustomerName;

public interface CustomerNameRepository extends JpaRepository<CustomerName, Long> {
    List<CustomerName> findAllByCrudValueNot(Character crudValue);
    Optional<CustomerName> findByIdAndCrudValueNot(Long id, Character crudValue);
}
