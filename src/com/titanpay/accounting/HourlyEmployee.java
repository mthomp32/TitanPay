package com.titanpay.accounting;

import java.util.ArrayList;
import java.util.Date;

public class HourlyEmployee extends Employee {
	
	private double hourlyRate;
	private ArrayList<TimeCard> timecards;

	public HourlyEmployee(int employeeId, String firstName, String lastName, 
	double weeklyDues, double hourlyRate, PaymentMethod payMethod) {
		super(employeeId, firstName, lastName, weeklyDues, payMethod);
		this.hourlyRate = hourlyRate;
		this.timecards = new ArrayList<TimeCard>();
	}

	@Override
	public String getFullName() {
		return this.getLastName() + ", " + this.getFirstName();
	}
	
	public void clockIn() {
		timecards.add(new TimeCard(new Date(), (double)System.currentTimeMillis(), 0));
	}
	
	public void clockOut() {
		for (TimeCard t: timecards) {
			Date timecardDate = t.getDate();
			Date currentDate = new Date();
			if (timecardDate == currentDate){
				t.setEndTime((double)System.currentTimeMillis());
			}
		}
	}

	@Override
	public void pay(Date startDate, Date endDate) {
		double pay = 0;
		for (TimeCard t: timecards) {
			if ((t.getDate().after(startDate) || t.getDate() == startDate) && 
			(t.getDate().before(endDate) || t.getDate() == endDate)) {
				pay += t.calculateDailyPay(10.00);
			}
		}
		this.getPayMethod().pay(this.getFullName(), pay);
		
	}
}
