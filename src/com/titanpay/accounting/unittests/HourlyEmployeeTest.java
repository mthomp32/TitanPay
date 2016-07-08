package com.titanpay.accounting.unittests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.titanpay.accounting.DirectDepositPayment;
import com.titanpay.accounting.HourlyEmployee;
import com.titanpay.accounting.TimeCard;

public class HourlyEmployeeTest {

	@Test
	public void testClockInAddTimeCard() {
		HourlyEmployee employee = new HourlyEmployee(1, "Thompson", "Mitchell", 18.00, 
		20.00, new DirectDepositPayment("DD"));
		
		employee.clockIn();
		ArrayList<TimeCard> tc = employee.getTimecards();
		
		assertEquals(tc.size(), 1);
	}
	
	@Test
	public void testClockIn_AddedTimeCardHasCurrentDate() {
		HourlyEmployee employee = new HourlyEmployee(1, "Thompson", "Mitchell", 18.00, 
		20.00, new DirectDepositPayment("DD"));
				
		employee.clockIn();
		ArrayList<TimeCard> tcards = employee.getTimecards();
		TimeCard tc = tcards.get(0);
				
		Date dt = new Date();
		assertEquals(tc.getDate(), dt);
	}

	@Test
	public void testClockOut() {
		HourlyEmployee employee = new HourlyEmployee(1, "Thompson", "Mitchell", 18.00, 
		20.00, new DirectDepositPayment("DD"));
		
		employee.clockIn();
		employee.clockOut();
		ArrayList<TimeCard> tc = employee.getTimecards();
		for (TimeCard t: tc) {
			assertNotNull(t.getEndTime());
		}
	}

	@Test
	public void testPay() {
		HourlyEmployee employee = new HourlyEmployee(1, "Thompson", "Mitchell", 18.00, 
		20.00, new DirectDepositPayment("DD"));
		
		employee.clockIn();
		employee.clockOut();
		
		
		assertNotNull(employee.pay(new Date(), new Date()));
	}

	@Test
	public void testReadTimesheet() throws FileNotFoundException, ParseException {
		HourlyEmployee employee = new HourlyEmployee(1, "Thompson", "Mitchell", 18.00, 
		20.00, new DirectDepositPayment("DD"));
		
		employee.readTimesheet();
		ArrayList<TimeCard> tc = employee.getTimecards();
		
		assertNotNull(tc.size());
	}

}
