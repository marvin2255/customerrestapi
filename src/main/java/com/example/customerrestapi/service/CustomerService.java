package com.example.customerrestapi.service;

import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.customerrestapi.exception.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import com.example.customerrestapi.apiresponse.ApiResponse;
import com.example.customerrestapi.exception.DatabaseException;
import com.example.customerrestapi.exception.NullCustomerException;

@Service
@Slf4j
@Validated
public class CustomerService implements CustomerServiceInterface { 

	@Autowired
	private CustomerRepository customerRepository;

	ApiResponse response = new ApiResponse();
	@Validated
	public ApiResponse saveCustomer(Customer customer) {
		log.info("Saving a new customer");
		
		Customer savedCustomer = customerRepository.save(customer);
		if (savedCustomer == null) {
			throw new DatabaseException("Database error: Unable to save customer");
		}
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setStatus("Success");
		response.setMessage("Customer with id " + savedCustomer.getId() + " was saved successfully");
		response.setData(savedCustomer);
		log.info("Saved a new customer");

		return response;

	}
	public ApiResponse getCustomerById(long id) {
		log.info("Getting customer with id {}", id);
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NullCustomerException("Customer with id " + id + " not found");
		}
		response.setStatus("Success");
		response.setMessage("Customer retrieved successfully");
		response.setData(customer.get());
		response.setStatusCode(HttpStatus.OK.value());
		log.info("all customer details with  the id {} is retrived ", id);

		return response;
	}

	public ApiResponse getAllCustomers() {
		log.info("Getting all customers");
		List<Customer> customers = customerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		if (customers.isEmpty()) {
			throw new NullCustomerException("No customers found");
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatus("Success");
		response.setMessage("Customers retrieved successfully");
		response.setData(customers);
		log.info("Got  all customers ");

		return response;
	}
	@Validated
	public ApiResponse updateCustomer(long id, Customer customer) {
		log.info("Updating customer with id {}", id);
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
		response.setStatus("Success");
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Customer with id " + id + " was updated successfully");
		response.setData(savedCustomer);
		log.info("Updated  customer with id {} successfully", id);

		return response;

	}

	public ApiResponse deleteCustomer(long id) {  
		log.info("Deleting customer with id {}", id);
		if (!customerRepository.existsById(id)) {
			throw new NullCustomerException("Customer with id " + id + " not found");
		}
		Optional<Customer> deletedCustomer = customerRepository.findById(id);
		customerRepository.deleteById(id);
		response.setStatus("success");
		response.setMessage("Customer with  id" + id + "is deleted successfully");
		response.setStatusCode(HttpStatus.NO_CONTENT.value());
		response.setData(deletedCustomer);

		log.info("Customer with id {} was deleted successfully", id);
		return response;
	}

}
