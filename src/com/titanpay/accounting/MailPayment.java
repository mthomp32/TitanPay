package com.titanpay.accounting;

public class MailPayment extends PaymentMethod {
	
	private Address address;
	
	public MailPayment (String name){
		super(name);
	}
	
	public MailPayment() {
		
	}

	@Override
	public String pay(String name, double payAmount) {
		return "Mailing a check to " + name + " for " + payAmount + " to " +
		this.address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
