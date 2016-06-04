package com.titanpay.accounting;

public class BankAccount {
	
	private String bankName;
	private int routingNumber;
	private String accountId;
	
	public BankAccount(String bankName, int routingNumber, String accountId) {
		this.bankName = bankName;
		this.routingNumber = routingNumber;
		this.accountId = accountId;
	}
	
	public void deposit(double amt) {
		System.out.println("Depositing $" + amt + " in " + bankName + 
		" Account Number: " + accountId + " using Routing Number: " +
		routingNumber);
	}

}
