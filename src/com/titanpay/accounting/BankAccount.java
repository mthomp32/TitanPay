package com.titanpay.accounting;

public class BankAccount {
	
	// Declare data variables for the BankAccount class
	private String bankName;
	private int routingNumber;
	private String accountId;
	
	// Create a constructor for a new BankAccount
	public BankAccount(String bankName, int routingNumber, String accountId) {
		this.bankName = bankName;
		this.routingNumber = routingNumber;
		this.accountId = accountId;
	}
	
	// Create a method to deposit money into a BankAccount
	public void deposit(double amt) {
		System.out.println("Depositing $" + amt + " in " + bankName + 
		" Account Number: " + accountId + " using Routing Number: " +
		routingNumber);
	}

}
