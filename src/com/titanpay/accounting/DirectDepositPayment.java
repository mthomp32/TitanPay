package com.titanpay.accounting;

public class DirectDepositPayment extends PaymentMethod {

	public DirectDepositPayment(String name, double payAmount) {
		super(name, payAmount);
	}
	
	@Override
	public String pay(String name, double payAmount) {
		return "A check for " + payAmount + " is being deposited for " + name + 
		" in their bank account.";
	}
}
