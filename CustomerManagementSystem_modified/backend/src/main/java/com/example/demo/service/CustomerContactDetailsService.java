package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CustomerContactDetails;

public interface CustomerContactDetailsService {

    CustomerContactDetails addCustomerContact(CustomerContactDetails contact);

    List<CustomerContactDetails> getAllContacts();

    CustomerContactDetails getContactById(Long id);

    CustomerContactDetails updateContact(Long id, CustomerContactDetails contact);

    void deleteContact(Long id);
}
