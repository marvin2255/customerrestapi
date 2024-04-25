package com.example.customerrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.customerrestapi.apiresponse.ApiResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
    ApiResponse response= new  ApiResponse();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        response.setData(null);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        response.setData(null);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiResponse> handleDatabaseException(DatabaseException e) {
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        response.setData(null);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoCustomersFoundException.class)
    public ResponseEntity<ApiResponse> handleNoCustomersFoundException(NoCustomersFoundException e) {
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        response.setData(null);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullCustomerException.class)
    public ResponseEntity<ApiResponse> handleNullCustomerException(NullCustomerException e) {
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        response.setData(null);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        response.setStatus("Failure");
        response.setMessage(e.getMessage());
        response.setData(null);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

