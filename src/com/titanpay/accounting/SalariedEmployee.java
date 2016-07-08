package com.titanpay.accounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalariedEmployee extends Employee implements Payable {
	
	@Id
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
	
	public void readReceipts() throws ParseException, FileNotFoundException {
    	Scanner scanner = new Scanner(new File("receipts.csv"));
    	scanner.useDelimiter(",");
    	scanner.nextLine();
    	while (scanner.hasNext()) {
    		int id = scanner.nextInt();
    		String lastName = scanner.next();
    		String item = scanner.next();
    		int unit = scanner.nextInt();
    		String cost = scanner.next();
    		String saleAmtValComma = scanner.nextLine();
    		String delims = "[,]";
    		String[] tokens = saleAmtValComma.split(delims);
    		String saleAmtSpace = tokens[1];
    		String saleAmtString = saleAmtSpace.trim();
			double saleAmt = saleAmtString == "" ? 0.00 : Double.parseDouble(saleAmtString);
    		Receipt receipt = new Receipt(new Date(), saleAmt);
    		if (id == this.getEmployeeId()) {
    			receipts.add(receipt);
    		}
    		
    	}
    	scanner.close();
	}
	
	public ArrayList<Receipt> getReceipts() {
		return this.receipts;
	}
}
