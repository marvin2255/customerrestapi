package com.example.customerrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerrestapi.apiresponse.*;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/addCustomer")
	public ApiResponse saveEmployee(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@GetMapping("/viewCustomerAll")
	public ApiResponse getCustomers() {
		return customerService.getAllCustomers();
	}

	@PutMapping("/updateCustomer/{id}")
	public ApiResponse updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public ApiResponse deleteEmployee(@PathVariable long id) {
		return customerService.deleteCustomer(id);
	}

	@GetMapping("/viewCustomer/{id}")
	public ApiResponse  getCustomerById(@PathVariable(value = "id") Long id) {
		return customerService.getCustomerById(id);
	}

}
