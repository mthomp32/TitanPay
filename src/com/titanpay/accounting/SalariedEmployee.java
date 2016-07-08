package com.titanpay.accounting;

import java.util.ArrayList;
import java.util.Date;

public class SalariedEmployee extends Employee implements Payable {
	
	private double salary;
	private double commissionRate;
	private ArrayList<Receipt> receipts;
	
	public SalariedEmployee(int employeeId, String lastName, String firstName, 
	double salary, double commissionRate, double weeklyDues, PaymentMethod payMethod) {
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
	public String pay(Date startDate, Date endDate) {
		double pay = salary;
		for (Receipt r : receipts) {
			if ((r.getDate().after(startDate) || r.getDate() == startDate) && 
			(r.getDate().before(endDate) || r.getDate() == endDate)) {
				pay += commissionRate * r.getSaleAmt();
			}
		}
		return this.getPayMethod().pay(this.getFullName(), pay);
		
	}
	
	public ArrayList<Receipt> getReceipts() {
		return this.receipts;
	}
}
