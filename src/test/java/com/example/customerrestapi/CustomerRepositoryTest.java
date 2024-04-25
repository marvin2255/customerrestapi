package com.example.customerrestapi;


import static org.junit.jupiter.api.Assertions.*;
import com.example.customerrestapi.model.Customer;
import com.example.customerrestapi.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)

class CustomerRepositoryTest {



    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
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
    public void testSaveCustomer() {
        Customer savedCustomer = customerRepository.save(testCustomer);
        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertEquals(savedCustomer, foundCustomer);
    }

    @Test
    public void testFindAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);
    }

    @Test
    public void testFindById() {
        Customer savedCustomer = customerRepository.save(testCustomer);
        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());
        assertTrue(foundCustomer.isPresent());
        assertEquals(savedCustomer, foundCustomer.get());
    }

    @Test
    public void testDeleteCustomer() {
        Customer savedCustomer = customerRepository.save(testCustomer);
        customerRepository.delete(savedCustomer);
        Optional<Customer> deletedCustomer = customerRepository.findById(savedCustomer.getId());
        assertTrue(deletedCustomer.isEmpty());
    }
}
