package com.example.customerrestapi.exception;

public class NullCustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullCustomerException(String message) {
        super(message);
    }
}