package com.titanpay.accounting;

import java.util.ArrayList;
import java.util.Date;

public class SalariedEmployee extends Employee implements Payable {
	
	private double salary;
	private double commissionRate;
	private ArrayList<Receipt> receipts;
	
	public SalariedEmployee(int employeeId, String firstName, String lastName, 
	double weeklyDues, double salary, double commissionRate, PaymentMethod payMethod) {
		super(employeeId, firstName, lastName, weeklyDues, payMethod);
		this.salary = salary;
		this.commissionRate = commissionRate;
		this.receipts = new ArrayList<Receipt>();
	}

	@Override
	public String getFullName() {
		return this.getLastName() + ", " + this.getFirstName();
	}
	
	public void makeSale(double amt) {
		receipts.add(new Receipt (new Date(), amt));
	}

	@Override
	public void pay(Date startDate, Date endDate) {
		double pay = salary;
		for (Receipt r : receipts) {
			if ((r.getDate().after(startDate) || r.getDate() == startDate) && 
			(r.getDate().before(endDate) || r.getDate() == endDate)) {
				pay += commissionRate * r.getSaleAmt();
			}
		}
		this.getPayMethod().pay(this.getFullName(), pay);
		
	}
}
