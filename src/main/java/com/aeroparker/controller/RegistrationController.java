package com.aeroparker.controller;

import com.aeroparker.model.Customer;
import com.aeroparker.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final CustomerRepository customerRepository;

    public RegistrationController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }

    @GetMapping("/success")
    public String showSuccessForm(Model model) {
        return "success";
    }

    @PostMapping
    public String addCustomer(@ModelAttribute("customer") Customer customer,
                              BindingResult result,
                              Model model) {

        if (customerRepository.findByEmailAddress(customer.getEmailAddress()).isPresent()) {
            result.rejectValue("emailAddress", "error.customer", "Email is already registered");
        }

        if (result.hasErrors()) {
            System.out.println("Error in submission");
            return "registration";
        }

        customer.setRegistered(LocalDateTime.now());
        customerRepository.save(customer);
        System.out.println("Confirmation email sent to:" + customer.getEmailAddress());
        return "success";
    }
}
