package com.titanpay.accounting;

public class MailPayment extends PaymentMethod {
	
	private Address address;
	
	public MailPayment (String name, double payAmount, Address address){
		super(name, payAmount);
		this.address = address;
	}

	@Override
	public String pay(String name, double payAmount) {
		return "Mailing a check to " + name + " for " + payAmount + " to " +
		this.address;
	}

	public Address getAddress() {
		return address;
	}
}
