package com.titanpay.accounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HourlyEmployee extends Employee {
	
	private double hourlyRate;
	private ArrayList<TimeCard> timecards;

	public HourlyEmployee(int employeeId, String lastName, String firstName, 
	double hourlyRate, double weeklyDues, PaymentMethod payMethod) {
		super(employeeId, firstName, lastName, weeklyDues, payMethod);
		this.hourlyRate = hourlyRate;
		this.timecards = new ArrayList<TimeCard>();
	}

	@Override
	public String getFullName() {
		return this.getLastName() + ", " + this.getFirstName();
	}
	
	public double getHourlyRate() {
		return hourlyRate;
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
	public String pay(Date startDate, Date endDate) {
		double pay = 0;
		for (TimeCard t: timecards) {
			if ((t.getDate().after(startDate) || t.getDate() == startDate) && 
			(t.getDate().before(endDate) || t.getDate() == endDate)) {
				pay += t.calculateDailyPay(this.getHourlyRate());
			}
		}
		return this.getPayMethod().pay(this.getFullName(), pay);
		
	}
	
	public void readTimesheet() throws ParseException, FileNotFoundException {
    	Scanner scanner = new Scanner(new File("timecards.csv"));
    	scanner.useDelimiter(",");
    	while (scanner.hasNext()) {
    		scanner.nextLine();
    		int id = scanner.nextInt();
    		int in = scanner.nextInt();
    		int out = scanner.nextInt();
    		String dateString = scanner.next();
    		DateFormat conversion = new SimpleDateFormat("MMMM d, yyyy");
    		Date date = conversion.parse(dateString);
    		TimeCard timecard = new TimeCard(date, in, out);
    		if (id == this.getEmployeeId()) {
    			timecards.add(timecard);
    		}
    		
    	}
    	scanner.close();
	}
}
