package com.titanpay.accounting;

public class Address {
	
	// Declare data variables for the Address class
	private String streetAddress;
	private String city;
	private String state;
	private int zip;
	
	// Create constructor for the Address class
	public Address (String streetAddress, String city, String state,
	int zip) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	// This method returns the full address
	public String getAddress() {
		return this.streetAddress + " " + this.city + ", " + this.state + " " 
		+ this.zip;
	}
}
