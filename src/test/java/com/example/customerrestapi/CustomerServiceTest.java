package com.example.customerrestapi;

import static org.junit.jupiter.api.Assertions.*;

import com.example.customerrestapi.apiresponse.*;
import com.example.customerrestapi.exception.NullCustomerException;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;
import com.example.customerrestapi.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

  
    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
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
        customers.add(new Customer(1L, "marvin", "g10x", "male", "12/02/1998", "324324234", "marvin@123", "user@123", "User$333"));
        when(customerRepository.findAll(any(Sort.class))).thenReturn(customers);

        ApiResponse result = customerService.getAllCustomers();

        assertEquals("Success", result.getStatus());
        assertEquals(customers, result.getData());
    }

    @Test
    public void testGetAllCustomersNoCustomer() {
        List<Customer> customers = new ArrayList<>();
        when(customerRepository.findAll(any(Sort.class))).thenReturn(customers);

        assertThrows(NullCustomerException.class, () -> {
            customerService.getAllCustomers();
        });
    }


    @Test
    public void testUpdateCustomer() {
        Customer initialCustomer = new Customer(1L, "marvin", "g10x", "male", "12/02/1998", "324324234", "marvin@123", "user@123", "User$333");
        
        Customer updatedCustomer = new Customer(1L, "marvin", "g10x", "male", "11/02/1998", "324324234", "marvin@123", "user@123", "User$333");

        
        when(customerRepository.findById(1L)).thenReturn(Optional.of(initialCustomer));
        
        when(customerRepository.save(initialCustomer)).thenReturn(updatedCustomer);

        ApiResponse result = customerService.updateCustomer(1L, updatedCustomer);
        
        assertEquals("Success", result.getStatus());
        
        assertEquals(updatedCustomer, result.getData());
    }


    @Test
    public void testDeleteCustomer() {
    	
        when(customerRepository.existsById(1L)).thenReturn(true);

        doNothing().when(customerRepository).deleteById(1L);

        ApiResponse result = customerService.deleteCustomer(1L);
        assertEquals("success", result.getStatus());
    }
}