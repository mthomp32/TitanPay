package com.titanpay.accounting;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Date date;
	private double startTime;
	private double endTime;
	
	public TimeCard(Date date, double startTime, double endTime) { 
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public double calculateDailyPay(double rate) {
		double totalPay = 0;
		double totalTime = endTime - startTime;
		double overTimeRate = rate * 1.5;
		if (totalTime > 8) {
			double extraHours = (endTime - startTime) - 8;
			totalPay = (rate * (endTime - startTime - extraHours) + 
			(overTimeRate * extraHours));
		}
		else {
			totalPay = rate * totalTime;
		}
		return totalPay;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
}
