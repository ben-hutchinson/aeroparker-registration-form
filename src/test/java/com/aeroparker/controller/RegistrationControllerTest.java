package com.aeroparker.controller;

import com.aeroparker.model.Customer;
import com.aeroparker.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    private RegistrationController registrationController;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationController = new RegistrationController(customerRepository);
    }

    @Test
    public void testShowRegistrationForm() {
        String view = registrationController.showRegistrationForm(model);
        assertEquals("registration", view);
        verify(model, times(1)).addAttribute(eq("customer"), any(Customer.class));
    }

    @Test
    public void testShowSuccessForm() {
        String view = registrationController.showSuccessForm(model);
        assertEquals("success", view);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setEmailAddress("test@example.com");

        when(customerRepository.findByEmailAddress("test@example.com"))
                .thenReturn(Optional.empty());
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = registrationController.addCustomer(customer, bindingResult, model);

        assertEquals("success", view);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testAddCustomerEmailAlreadyRegistered() {
        Customer customer = new Customer();
        customer.setEmailAddress("test@example.com");

        when(customerRepository.findByEmailAddress("test@example.com"))
                .thenReturn(Optional.of(customer));
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = registrationController.addCustomer(customer, bindingResult, model);

        assertEquals("registration", view);
        verify(bindingResult, times(1)).rejectValue("emailAddress", "error.customer", "Email is already registered");
        verify(customerRepository, never()).save(any());
    }

    @Test
    public void testAddCustomerWithValidationErrors() {
        Customer customer = new Customer();
        customer.setEmailAddress("new@example.com");

        when(customerRepository.findByEmailAddress("new@example.com"))
                .thenReturn(Optional.empty());
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = registrationController.addCustomer(customer, bindingResult, model);

        assertEquals("registration", view);
        verify(customerRepository, never()).save(any());
    }
}
