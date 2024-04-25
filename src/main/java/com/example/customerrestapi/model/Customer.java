package com.example.customerrestapi.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerGender() {
		return customerGender;
	}
	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}
	public String getCustomerDob() {
		return customerDob;
	}
	public void setCustomerDob(String customerDob) {
		this.customerDob = customerDob;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerUserName() {
		return customerUserName;
	}
	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public Customer(Long id, String customerName, String customerAddress, String customerGender, String customerDob,
			String customerPhoneNumber, String customerEmail, String customerUserName, String customerPassword) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerGender = customerGender;
		this.customerDob = customerDob;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.customerUserName = customerUserName;
		this.customerPassword = customerPassword;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", customerGender=" + customerGender + ", customerDob=" + customerDob + ", customerPhoneNumber="
				+ customerPhoneNumber + ", customerEmail=" + customerEmail + ", customerUserName=" + customerUserName
				+ ", customerPassword=" + customerPassword + "]";
	}

	
}