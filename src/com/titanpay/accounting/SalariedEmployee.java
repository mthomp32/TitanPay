package com.titanpay.accounting;

public class SalariedEmployee extends Employee {
	
	private double salary;
	private double commissionRate;
	
	public SalariedEmployee(int employeeId, String firstName, String lastName, 
	double weeklyDues, double salary, double commissionRate) {
		super(employeeId, firstName, lastName, weeklyDues);
		this.salary = salary;
		this.commissionRate = commissionRate;
	}
}
