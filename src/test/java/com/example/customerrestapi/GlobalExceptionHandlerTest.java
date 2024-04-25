package com.example.customerrestapi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.example.customerrestapi.apiresponse.ApiResponse;
import com.example.customerrestapi.exception.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument exception");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Failure", responseEntity.getBody().getStatus());
        assertEquals("Illegal argument exception", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatusCode());
    }
    @Test
    public void testHandleCustomerNotFoundException() {
        CustomerNotFoundException exception = new CustomerNotFoundException("Customer not found exception");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleCustomerNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Failure", responseEntity.getBody().getStatus());
        assertEquals("Customer not found exception", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatusCode());
    }

    @Test
    public void testHandleDatabaseException() {
        DatabaseException exception = new DatabaseException("Database exception");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleDatabaseException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failure", responseEntity.getBody().getStatus());
        assertEquals("Database exception", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatusCode());
    }

    @Test
    public void testHandleNoCustomersFoundException() {
        NoCustomersFoundException exception = new NoCustomersFoundException("No customers found exception");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleNoCustomersFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Failure", responseEntity.getBody().getStatus());
        assertEquals("No customers found exception", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatusCode());
    }

    @Test
    public void testHandleNullCustomerException() {
        NullCustomerException exception = new NullCustomerException("Null customer exception");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleNullCustomerException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Failure", responseEntity.getBody().getStatus());
        assertEquals("Null customer exception", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatusCode());
    }

    @Test
    public void testHandleRuntimeException() {
        RuntimeException exception = new RuntimeException("Runtime exception");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handleRuntimeException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failure", responseEntity.getBody().getStatus());
        assertEquals("Runtime exception", responseEntity.getBody().getMessage());
        assertEquals(null, responseEntity.getBody().getData());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatusCode());
    }
}