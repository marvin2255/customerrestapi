package com.example.customerrestapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//lombok used for creating  getters setters and constructors
@Entity
@Data

@AllArgsConstructor

@NoArgsConstructor

public class Customer {
	private @Id @GeneratedValue Long id;
	private String customerName;
	private String customerAddress;
	private String customerGender;
	private String customerDob;
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerUserName;
	private String customerPassword;

}