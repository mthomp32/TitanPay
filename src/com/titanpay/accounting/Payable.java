package com.titanpay.accounting;

import java.util.Date;

public interface Payable {
	
	public void pay(Date startDate, Date endDate);

}
