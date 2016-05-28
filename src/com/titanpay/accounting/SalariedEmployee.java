package com.titanpay.accounting;

public class SalariedEmployee {
	
	// Declare data variables
	private int employeeId;
	private String firstName;
	private String lastName;
	private double commissionRate;
	private double weeklyDues;
	
	// Create constructor for the SalariedEmployee class
	public SalariedEmployee (int employeeId, String firstName, String lastName,
	double commissionRate, double weeklyDues) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.commissionRate = commissionRate;
		this.weeklyDues = weeklyDues;
	}
	
	// This method returns the full name of the salaried employee
	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}

}
