package com.example.customerrestapi.apiresponse;

import lombok.Getter;
import lombok.Setter;
//used lombok  for creating getters and setters
@Getter
@Setter
public class ApiResponse {

	private int statusCode;
	private String status;
	private String message;
	private Object data;


}