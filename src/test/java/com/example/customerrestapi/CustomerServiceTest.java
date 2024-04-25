package com.example.customerrestapi;

import static org.junit.jupiter.api.Assertions.*;

import com.example.customerrestapi.apiresponse.*;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;
import com.example.customerrestapi.service.CustomerService;
import com.example.customerrestapi.validation.CustomerValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerValidator customerValidator;

	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer();
		when(customerValidator.validate(customer)).thenReturn(true);
		when(customerRepository.save(customer)).thenReturn(customer);

		ApiResponse result = customerService.saveCustomer(customer);
		assertEquals("Success", result.getStatus());
		assertEquals(customer, result.getData());
	}

	@Test
	public void testGetCustomerById() {
		Customer customer = new Customer();
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		ApiResponse result = customerService.getCustomerById(1L);
		assertEquals("Success", result.getStatus());
		assertEquals(customer, result.getData());
	}

	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		when(customerRepository.findAll(any(Sort.class))).thenReturn(customers);

		ApiResponse result = customerService.getAllCustomers();
		assertEquals("Success", result.getStatus());
		assertEquals(customers, result.getData());
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer();
		when(customerValidator.validate(customer)).thenReturn(true);
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRepository.save(customer)).thenReturn(customer);
	}

	@Test
	public void testDeleteCustomer() {
		doNothing().when(customerRepository).deleteById(1L);
		when(customerRepository.existsById(1L)).thenReturn(true);

		ApiResponse result = customerService.deleteCustomer(1L);
		assertEquals("Success", result.getStatus());
	}

}
