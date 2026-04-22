package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CustomerAddress;

public interface CustomerAddressService {

    CustomerAddress addAddress(CustomerAddress address);

    List<CustomerAddress> getAllAddresses();

    CustomerAddress getAddressById(Long id);

    CustomerAddress updateAddress(Long id, CustomerAddress address);

    void deleteAddress(Long id);
}
