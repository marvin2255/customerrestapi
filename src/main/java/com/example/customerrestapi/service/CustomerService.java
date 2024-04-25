package com.example.customerrestapi.service;

import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;
import com.example.customerrestapi.validation.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.customerrestapi.exception.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import com.example.customerrestapi.apiresponse.ApiResponse;
import com.example.customerrestapi.exception.DatabaseException;
import com.example.customerrestapi.exception.NullCustomerException;


@Service
public class CustomerService implements CustomerServiceInterface {

	@Autowired
	private CustomerRepository customerRepository;

	CustomerValidator customerValidator = new CustomerValidator();
	ApiResponse response = new ApiResponse();

	public ApiResponse saveCustomer(Customer customer) {
		if (!customerValidator.validate(customer)) {
			throw new IllegalArgumentException("Invalid customer data");
		}
		Customer savedCustomer = customerRepository.save(customer);
		if (savedCustomer == null) {
			throw new DatabaseException("Database error: Unable to save customer");
		}
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setStatus("Success");
		response.setMessage("Customer with id " + savedCustomer.getId() + " was saved successfully");
		response.setData(savedCustomer);
		return response;
	}

	public ApiResponse getCustomerById(long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NullCustomerException("Customer with id " + id + " not found");
		}
		response.setStatus("Success");
		response.setMessage("Customer retrieved successfully");
		response.setData(customer.get());
		response.setStatusCode(HttpStatus.OK.value());

		return response;
	}

	public ApiResponse getAllCustomers() {
		List<Customer> customers = customerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		if (customers.isEmpty()) {
			throw new NullCustomerException("No customers found");
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatus("Success");
		response.setMessage("Customers retrieved successfully");
		response.setData(customers);
		return response;
	}

	public ApiResponse updateCustomer(long id, Customer customer) {
		if (!customerValidator.validate(customer)) {
			throw new IllegalArgumentException("Invalid customer data");
		}
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer with id " + id + " not found");
		}
		Customer customerToUpdate = optionalCustomer.get();
		customerToUpdate.setCustomerName(customer.getCustomerName());
		customerToUpdate.setCustomerAddress(customer.getCustomerAddress());
		customerToUpdate.setCustomerGender(customer.getCustomerGender());
		customerToUpdate.setCustomerDob(customer.getCustomerDob());
		customerToUpdate.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
		customerToUpdate.setCustomerEmail(customer.getCustomerEmail());
		customerToUpdate.setCustomerUserName(customer.getCustomerUserName());
		customerToUpdate.setCustomerPassword(customer.getCustomerPassword());

		Customer savedCustomer = customerRepository.save(customerToUpdate);
		if (savedCustomer == null) {
			throw new DatabaseException("Database error: Unable to update customer");
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Customer with id " + id + " was updated successfully");
		response.setData(savedCustomer);
		return response;
	}

	public ApiResponse deleteCustomer(long id) {
		if (!customerRepository.existsById(id)) {
			throw new NullCustomerException("Customer with id " + id + " not found");
		}
		customerRepository.deleteById(id);
		if (customerRepository.existsById(id)) {
			throw new DatabaseException("Database error: Unable to delete customer with id " + id);
		}
		response.setStatus("Success");
		response.setStatusCode(HttpStatus.NO_CONTENT.value());
		response.setMessage("Customer with id " + id + " was deleted successfully");
		return response;
	}
}
