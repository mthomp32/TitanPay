package com.titanpay.accounting;

import java.util.Date;

public interface Payable {
	
	public String pay(Date startDate, Date endDate);

}
