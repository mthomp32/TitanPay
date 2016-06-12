package com.titanpay.accounting;

import java.util.ArrayList;
import java.util.Date;

public class HourlyEmployee extends Employee implements Payable {
	
	private double hourlyRate;
	private ArrayList<TimeCard> timecards;

	public HourlyEmployee(int employeeId, String firstName, String lastName, 
	double weeklyDues, double hourlyRate, PaymentMethod payMethod) {
		super(employeeId, firstName, lastName, weeklyDues, payMethod);
		this.hourlyRate = hourlyRate;
		this.timecards = new ArrayList<TimeCard>();
	}

	@Override
	public String getFullName(String lastName, String firstName) {
		return lastName + ", " + firstName;
	}
	
	public void clockIn() {
		timecards.add(new TimeCard(new Date(), (double)System.currentTimeMillis(), 0));
	}
	
	public void clockOut(Date currentDate) {
		for (TimeCard t: timecards) {
			t.setEndTime((double)System.currentTimeMillis());
		}
	}

	@Override
	public void pay(Date startDate, Date endDate) {
		double pay = 0;
		for (TimeCard t: timecards) {
			pay = t.calculateDailyPay(10.00);
		}
		this.getPayMethod().pay(this.getFullName
		(this.getLastName(), this.getFirstName()), pay);
	}
}
