package com.example.customerrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerrestapi.apiresponse.ApiResponse;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	

	
	

	@PostMapping("/v1/customer/create")
	public ResponseEntity<ApiResponse> saveEmployee(@RequestBody Customer customer) {
		log.info("Creating a new customer");
		return new ResponseEntity<ApiResponse>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}

	@GetMapping("/v1/customer/viewAll")
	public ResponseEntity<ApiResponse> getCustomers() {
		log.info("Getting all customers");
		return new ResponseEntity<ApiResponse>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@PutMapping("/v1/customer/{id}/update")
	public ResponseEntity<ApiResponse> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
		log.info("Updating customer with id {}", id);
		return new ResponseEntity<ApiResponse>(customerService.updateCustomer(id, customer), HttpStatus.OK);
	}

	@DeleteMapping("/v1/customer/{id}/delete")
	public  ResponseEntity<ApiResponse> deleteEmployee(@PathVariable long id) {
		log.info("Deleting customer with id {}", id);
		return  new  ResponseEntity<ApiResponse>(customerService.deleteCustomer(id),HttpStatus.OK);
	}

	@GetMapping("/v1/customer/{id}/view")
	public ResponseEntity<ApiResponse>  getCustomerById(@PathVariable(value = "id") Long id) {
		log.info("Getting customer with id {}", id);
		return new ResponseEntity<ApiResponse>(customerService.getCustomerById(id),HttpStatus.OK) ;
	}

}