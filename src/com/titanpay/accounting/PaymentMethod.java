package com.titanpay.accounting;

public abstract class PaymentMethod {
	
	private String name;
	private double payAmount;
	
	public PaymentMethod(String name, double payAmount) {
		this.name = name;
		this.payAmount = payAmount;
	}
	
	public abstract String pay(String name, double payAmount);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
}
