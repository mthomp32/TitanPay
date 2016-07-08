package com.titanpay.accounting.unittests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.titanpay.accounting.TimeCard;

public class TimeCardTest {

	@Test
	public void testCalculateDailyPay() {
		TimeCard timecard = new TimeCard(new Date(), 900, 1700);
		
		assertNotNull(timecard.calculateDailyPay(15.00));
	}
}
