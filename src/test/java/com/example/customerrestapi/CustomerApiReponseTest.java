package com.example.customerrestapi;

import com.example.customerrestapi.apiresponse.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerApiReponseTest {

	@Test
	public void testApiResponse() {
		ApiResponse apiResponse = new ApiResponse();

		// Set values
		apiResponse.setStatusCode(200);
		apiResponse.setStatus("Success");
		apiResponse.setMessage("Test message");
		apiResponse.setData("Test data");

		// Assert values
		assertEquals(200, apiResponse.getStatusCode());
		assertEquals("Success", apiResponse.getStatus());
		assertEquals("Test message", apiResponse.getMessage());
		assertEquals("Test data", apiResponse.getData());
	}

}
