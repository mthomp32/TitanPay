package com.titanpay.accounting.unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.titanpay.accounting.DirectDepositPayment;
import com.titanpay.accounting.Receipt;
import com.titanpay.accounting.SalariedEmployee;

public class SalariedEmployeeTest {

	@Test
	public void testMakeSale() {
		SalariedEmployee employee = new SalariedEmployee(1, "Thompson", "Mitchell", 50000.00, 
		8.00, 50.00, new DirectDepositPayment("DD"));
		
		employee.makeSale(1000);
		ArrayList<Receipt> r = employee.getReceipts();
		
		assertEquals(r.size(), 1);
	}

	@Test
	public void testPay() {
		SalariedEmployee employee = new SalariedEmployee(1, "Thompson", "Mitchell", 50000.00, 
		8.00, 50.00, new DirectDepositPayment("DD"));
				
		employee.makeSale(1000);
		employee.getReceipts();
				
		assertNotNull(employee.pay(new Date(), new Date()));
	}

}
