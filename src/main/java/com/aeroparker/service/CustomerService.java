package com.aeroparker.service;


import com.aeroparker.model.Customer;
import com.aeroparker.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public boolean isEmailTaken(String email) {
        return repository.existsByEmailAddress(email);
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }
}
