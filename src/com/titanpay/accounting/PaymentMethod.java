package com.titanpay.accounting;

public abstract class PaymentMethod {
	
	private String name;
	
	public PaymentMethod(String name) {
		this.name = name;
	}
	
	public abstract String pay(String name, double payAmount);

	public String getName() {
		return name;
	}
}
