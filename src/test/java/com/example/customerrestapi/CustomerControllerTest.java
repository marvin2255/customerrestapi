package com.example.customerrestapi;
import com.example.customerrestapi.apiresponse.ApiResponse;
import com.example.customerrestapi.controller.CustomerController;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;
import com.example.customerrestapi.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CustomerControllerTest {
	   @InjectMocks
	    CustomerController customerController;

	
	

	@MockBean
	private CustomerService customerService;

	@InjectMocks
	private Customer testCustomer;
	@Mock
	private CustomerRepository customerRepository;

	@BeforeEach
	void setUp() {
		testCustomer = new Customer();
		testCustomer.setId(1L);
		testCustomer.setCustomerName("John Doe");
		testCustomer.setCustomerAddress("123 Main St, Anytown, USA");
		testCustomer.setCustomerGender("Male");
		testCustomer.setCustomerDob("02-07-1998");
		testCustomer.setCustomerPhoneNumber("123-456-7890");
		testCustomer.setCustomerEmail("john.doe@example.com");
		testCustomer.setCustomerUserName("johndoe");
		testCustomer.setCustomerPassword("password123");
	} 


    @Test
    public void testSaveEmployee() {
        Customer customer = new Customer();
        ApiResponse apiResponse = new ApiResponse();
        when(customerService.saveCustomer(customer)).thenReturn(apiResponse);

        ResponseEntity<ApiResponse> response = customerController.saveEmployee(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void testGetCustomers() {
        ApiResponse apiResponse = new ApiResponse();
        when(customerService.getAllCustomers()).thenReturn(apiResponse);

        ResponseEntity<ApiResponse> response = customerController.getCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).getAllCustomers();
    }
    @Test
    public void testUpdateCustomer() {
        long id = 1L;
        Customer customer = new Customer();
        ApiResponse apiResponse = new ApiResponse();
        when(customerService.updateCustomer(id, customer)).thenReturn(apiResponse);

        ResponseEntity<ApiResponse> response = customerController.updateCustomer(id, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).updateCustomer(id, customer);
    }

    @Test
    public void testDeleteEmployee() {
        long id = 1L;
        ApiResponse apiResponse = new ApiResponse();
        when(customerService.deleteCustomer(id)).thenReturn(apiResponse);

        ResponseEntity<ApiResponse> response = customerController.deleteEmployee(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(id);
    }

    @Test
    public void testGetCustomerById() {
        long id = 1L;
        ApiResponse apiResponse = new ApiResponse();
        when(customerService.getCustomerById(id)).thenReturn(apiResponse);
        ResponseEntity<ApiResponse> response = customerController.getCustomerById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).getCustomerById(id);
    }

  
}