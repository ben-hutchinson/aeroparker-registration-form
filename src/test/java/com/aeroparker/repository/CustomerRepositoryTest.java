package com.aeroparker.repository;

import com.aeroparker.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRepositoryTest {

    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
    }

    @Test
    void testExistsByEmailAddress() {
        String email = "test@example.com";

        when(customerRepository.existsByEmailAddress(email)).thenReturn(true);

        boolean exists = customerRepository.existsByEmailAddress(email);

        assertTrue(exists);
        verify(customerRepository, times(1)).existsByEmailAddress(email);
    }

    @Test
    void testFindByEmailAddressSuccess() {
        String email = "found@example.com";
        Customer customer = new Customer();
        customer.setEmailAddress(email);

        when(customerRepository.findByEmailAddress(email)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerRepository.findByEmailAddress(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmailAddress());
        verify(customerRepository, times(1)).findByEmailAddress(email);
    }

    @Test
    void testFindByEmailAddressIgnoreCaseFail() {
        String email = "missing@example.com";

        when(customerRepository.findByEmailAddress(email)).thenReturn(Optional.empty());

        Optional<Customer> result = customerRepository.findByEmailAddress(email);

        assertFalse(result.isPresent());
        verify(customerRepository, times(1)).findByEmailAddress(email);
    }
}
