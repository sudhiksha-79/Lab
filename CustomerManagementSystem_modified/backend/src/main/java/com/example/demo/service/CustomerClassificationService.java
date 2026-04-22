package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CustomerClassification;

public interface CustomerClassificationService {

    CustomerClassification add(CustomerClassification entity);

    List<CustomerClassification> getAll();

    CustomerClassification getById(Long id);

    CustomerClassification update(Long id, CustomerClassification entity);

    void delete(Long id);
}
