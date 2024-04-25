package com.example.customerrestapi;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;
import com.example.customerrestapi.service.CustomerService;
import com.example.customerrestapi.validation.CustomerValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Mock
	private CustomerValidator customerValidator;

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
	public void testAddCustomer() throws Exception {
		Customer customer = new Customer(); // construct a valid customer object
		Mockito.when(customerService.saveCustomer(Mockito.any(Customer.class))).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.post("/addCustomer").content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetCustomers() throws Exception {
		Mockito.when(customerService.getAllCustomers()).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewCustomerAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateCustomer1() throws Exception {
		long id = 1;
		Customer customer = new Customer(); // construct a valid customer object
		Mockito.when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(Customer.class))).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateCustomer/" + id).content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteCustomer1() throws Exception {
		long id = 1;
		Mockito.when(customerService.deleteCustomer(Mockito.anyLong())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCustomer/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetCustomerById1() throws Exception {
		long id = 1;
		Mockito.when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewCustomer/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}