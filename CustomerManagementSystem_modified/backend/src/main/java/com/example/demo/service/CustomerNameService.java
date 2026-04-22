package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CustomerName;

public interface CustomerNameService {

    CustomerName add(CustomerName name);

    List<CustomerName> getAll();

    CustomerName getById(Long id);

    CustomerName update(Long id, CustomerName name);

    void delete(Long id);
}
