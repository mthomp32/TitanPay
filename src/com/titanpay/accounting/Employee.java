package com.titanpay.accounting;

public class Employee {
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private double weeklyDues;
	
	public Employee (int employeeId, String firstName, String lastName, 
	double weeklyDues) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.weeklyDues = weeklyDues;
	}
	
	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}
}
