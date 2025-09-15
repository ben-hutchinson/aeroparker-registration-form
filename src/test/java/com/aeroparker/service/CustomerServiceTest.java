package com.aeroparker.service;

import com.aeroparker.model.Customer;
import com.aeroparker.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsEmailTaken() {
        String email = "test@example.com";
        when(repository.existsByEmailAddress(email)).thenReturn(true);

        boolean result = service.isEmailTaken(email);

        assertTrue(result);
        verify(repository).existsByEmailAddress(email);
    }

    @Test
    void testIsEmailNotTaken() {
        String email = "new@example.com";
        when(repository.existsByEmailAddress(email)).thenReturn(false);

        boolean result = service.isEmailTaken(email);

        assertFalse(result);
        verify(repository).existsByEmailAddress(email);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer("Mr", "John", "Doe",
                "john.doe@example.com", "123 Street", "", "City", "12345", "0123456789");

        when(repository.save(customer)).thenReturn(customer);

        Customer saved = service.saveCustomer(customer);

        assertNotNull(saved);
        assertEquals(customer.getEmailAddress(), saved.getEmailAddress());
        verify(repository).save(customer);
    }
}
