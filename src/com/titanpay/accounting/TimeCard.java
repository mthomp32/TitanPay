package com.titanpay.accounting;

public class TimeCard {
	
	// Declare data variables for the TimeCard class
	private String date;
	private int startTime;
	private int endTime;
	
	// Create constructor for new TimeCard
	public TimeCard(String date, int startTime, int endTime) { 
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	// Create a method to calculate the total daily pay
	public void calculateDailyPay(double rate) {
		int totalTime = endTime - startTime;
		double overTimeRate = rate * 1.5;
		if (totalTime > 8) {
			int extraHours = (endTime - startTime) - 8;
			double totalPay = (rate * (endTime - startTime - extraHours) + 
			overTimeRate * extraHours);
		}
		else {
			double totalPay = rate * totalTime;
		}
	}
}
