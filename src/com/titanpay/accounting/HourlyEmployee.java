package com.titanpay.accounting;

public class HourlyEmployee extends Employee {
	
	private double hourlyRate;

	public HourlyEmployee(int employeeId, String firstName, String lastName, 
	double weeklyDues, double hourlyRate) {
		super(employeeId, firstName, lastName, weeklyDues);
		this.hourlyRate = hourlyRate;
	}

}
