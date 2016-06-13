package com.titanpay.accounting;

public abstract class Employee implements Payable {
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private double weeklyDues;
	private PaymentMethod payMethod;
	
	public Employee (int employeeId, String firstName, String lastName, 
	double weeklyDues, PaymentMethod payMethod) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.weeklyDues = weeklyDues;
		this.payMethod = payMethod;
	}
	
	public  abstract String getFullName();

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getWeeklyDues() {
		return weeklyDues;
	}

	public void setWeeklyDues(double weeklyDues) {
		this.weeklyDues = weeklyDues;
	}

	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}
}
