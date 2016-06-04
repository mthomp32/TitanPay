package com.titanpay.accounting;

public class TimeCard {
	
	private String date;
	private int startTime;
	private int endTime;
	
	public TimeCard(String date, int startTime, int endTime) { 
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public void calculateDailyPay(double rate) {
		int totalTime = endTime - startTime;
		double overTimeRate = rate * 1.5;
		if (totalTime > 8) {
			int extraHours = (endTime - startTime) - 8;
			double totalPay = (rate * (endTime - startTime - extraHours) + 
			(overTimeRate * extraHours));
		}
		else {
			double totalPay = rate * totalTime;
		}
	}
}
