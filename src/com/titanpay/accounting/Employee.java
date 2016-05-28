package com.titanpay.accounting;

public class Employee {
	
	// Declare data variables
	private int employeeId;
	private String firstName;
	private String lastName;
	private double hourlyRate;
	private double weeklyDues;
	
	// Create constructor for the Employee class
	public Employee (int employeeId, String firstName, String lastName,
	double hourlyRate, double weeklyDues) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hourlyRate = hourlyRate;
		this.weeklyDues = weeklyDues;
	}
	
	// This method returns the full name of the employee
	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}

}
