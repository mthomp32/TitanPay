package com.titanpay.accounting;

import java.util.Date;

public class Receipt {
	
	private Date date;
	private double saleAmt;
	
	public Receipt(Date date, double saleAmt) {
		this.date = date;
		this.saleAmt = saleAmt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getSaleAmt() {
		return saleAmt;
	}

	public void setSaleAmt(double saleAmt) {
		this.saleAmt = saleAmt;
	}
}
