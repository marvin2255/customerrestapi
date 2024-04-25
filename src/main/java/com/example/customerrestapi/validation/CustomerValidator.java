package com.example.customerrestapi.validation;

import org.springframework.stereotype.Component;

import com.example.customerrestapi.model.Customer;

@Component
public class CustomerValidator {

    public boolean validate(Customer customer) {
        if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
            return false;
        }
        if (customer.getCustomerAddress() == null || customer.getCustomerAddress().isEmpty()) {
            return false;
        }
        if (customer.getCustomerGender() == null || customer.getCustomerGender().isEmpty()) {
            return false;
        }
        if (customer.getCustomerDob() == null || customer.getCustomerDob().isEmpty()) {
            return false;
        }
        if (customer.getCustomerPhoneNumber() == null || customer.getCustomerPhoneNumber().isEmpty()) {
            return false;
        }
        if (customer.getCustomerEmail() == null || customer.getCustomerEmail().isEmpty()) {
            return false;
        }
        if (customer.getCustomerUserName() == null || customer.getCustomerUserName().isEmpty()) {
            return false;
        }
        if (customer.getCustomerPassword() == null || customer.getCustomerPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}