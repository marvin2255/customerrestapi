package com.example.customerrestapi;

import static org.junit.jupiter.api.Assertions.*;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.validation.CustomerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerValidatorTest {

	private CustomerValidator customerValidator;
	private Customer customer;

	@BeforeEach
	public void setUp() {
		customerValidator = new CustomerValidator();
		customer = new Customer();
		customer.setCustomerName("John Doe");
		customer.setCustomerAddress("123 Main St, Anytown, USA");
		customer.setCustomerGender("Male");
		customer.setCustomerDob("02-07-1998");
		customer.setCustomerPhoneNumber("123-456-7890");
		customer.setCustomerEmail("john.doe@example.com");
		customer.setCustomerUserName("johndoe");
		customer.setCustomerPassword("password123");
	}

	@Test
	public void testValidate() {
		assertTrue(customerValidator.validate(customer));

		customer.setCustomerName(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerName("John Doe");
		customer.setCustomerAddress(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerAddress("123 Main St, Anytown, USA");
		customer.setCustomerGender(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerGender("Male");
		customer.setCustomerDob(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerDob("02-07-1998");
		customer.setCustomerPhoneNumber(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerPhoneNumber("123-456-7890");
		customer.setCustomerEmail(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerEmail("john.doe@example.com");
		customer.setCustomerUserName(null);
		assertFalse(customerValidator.validate(customer));

		customer.setCustomerUserName("johndoe");
		customer.setCustomerPassword(null);
		assertFalse(customerValidator.validate(customer));
	}
}
