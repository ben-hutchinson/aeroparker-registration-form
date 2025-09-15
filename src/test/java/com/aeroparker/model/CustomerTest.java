package com.aeroparker.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testDefaultConstructorAndSetters() {
        Customer customer = new Customer();

        LocalDateTime now = LocalDateTime.now();
        customer.setRegistered(now);
        customer.setTitle("Mr");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmailAddress("john.doe@example.com");
        customer.setAddressLine1("123 Main St");
        customer.setAddressLine2("Apt 4B");
        customer.setCity("London");
        customer.setPostcode("E1 6AN");
        customer.setPhoneNumber("07123456789");
        customer.setId(1L);

        assertEquals(1L, customer.getId());
        assertEquals(now, customer.getRegistered());
        assertEquals("Mr", customer.getTitle());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmailAddress());
        assertEquals("123 Main St", customer.getAddressLine1());
        assertEquals("Apt 4B", customer.getAddressLine2());
        assertEquals("London", customer.getCity());
        assertEquals("E1 6AN", customer.getPostcode());
        assertEquals("07123456789", customer.getPhoneNumber());
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDateTime registered = LocalDateTime.now();

        Customer customer = new Customer(
                "Ms",
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "456 Elm St",
                "Suite 12",
                "Manchester",
                "M1 2AB",
                "07234567890"
        );
        customer.setRegistered(registered);
        customer.setId(2L);

        assertEquals(2L, customer.getId());
        assertEquals(registered, customer.getRegistered());
        assertEquals("Ms", customer.getTitle());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("jane.smith@example.com", customer.getEmailAddress());
        assertEquals("456 Elm St", customer.getAddressLine1());
        assertEquals("Suite 12", customer.getAddressLine2());
        assertEquals("Manchester", customer.getCity());
        assertEquals("M1 2AB", customer.getPostcode());
        assertEquals("07234567890", customer.getPhoneNumber());
    }
}

