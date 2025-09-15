package com.aeroparker.repository;


import com.aeroparker.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmailAddress(String emailAddress);
    Optional<Customer> findByEmailAddress(String emailAddress);
}
