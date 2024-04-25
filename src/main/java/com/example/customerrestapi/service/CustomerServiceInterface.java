package com.example.customerrestapi.service;

import com.example.customerrestapi.apiresponse.ApiResponse;
import com.example.customerrestapi.model.Customer;

public interface CustomerServiceInterface {
	ApiResponse saveCustomer(Customer customer);

	ApiResponse getCustomerById(long id);

	ApiResponse getAllCustomers();

	ApiResponse updateCustomer(long id, Customer customer);

	ApiResponse deleteCustomer(long id);

}
