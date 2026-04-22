package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CustomerDetails;

public interface CustomerDetailsService {

    // CREATE
    CustomerDetails addCustomerDetails(CustomerDetails customerDetails);

    // READ ALL
    List<CustomerDetails> getAllCustomers();

    // READ BY ID
    CustomerDetails getCustomerById(Long id);

    // UPDATE
    CustomerDetails updateCustomer(Long id, CustomerDetails customerDetails);

    // DELETE
    void deleteCustomer(Long id);
}
