package com.titanpay.accounting;

public class PickUpPayment extends PaymentMethod {
	
	public PickUpPayment (String name, double payAmount){
		super(name);
	}

	@Override
	public String pay(String name, double payAmount) {
		return "A check for " + payAmount + " is waiting for " + name + 
		" at the PostMaster.";
	}
}
