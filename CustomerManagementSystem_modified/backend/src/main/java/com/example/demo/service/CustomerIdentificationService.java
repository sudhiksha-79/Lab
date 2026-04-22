package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CustomerIdentification;

public interface CustomerIdentificationService {

    CustomerIdentification addIdentification(CustomerIdentification identification);

    List<CustomerIdentification> getAllIdentifications();

    CustomerIdentification getIdentificationById(Long id);

    CustomerIdentification updateIdentification(Long id, CustomerIdentification identification);

    void deleteIdentification(Long id);
}
