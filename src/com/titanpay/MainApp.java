package com.titanpay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import com.titanpay.accounting.DirectDepositPayment;
import com.titanpay.accounting.HourlyEmployee;
import com.titanpay.accounting.MailPayment;
import com.titanpay.accounting.PickUpPayment;
import com.titanpay.accounting.Receipt;
import com.titanpay.accounting.SalariedEmployee;
import com.titanpay.accounting.TimeCard;
import com.titanpay.view.RunPayrollController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class MainApp extends Application {
	
	private Stage primaryStage;
	private AnchorPane runPayroll;
	private ArrayList<SalariedEmployee> salariedEmployees;
    private ArrayList<HourlyEmployee> hourlyEmployees;
    
    public void readHourlyEmployees() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("hourly_employees.csv"));
		scanner.useDelimiter(",");
		scanner.nextLine();
		String directDeposit = "DD";
		String pickUp = "PU";
		while (scanner.hasNext()) {
			int employeeId = scanner.nextInt();
			String lastName = scanner.next();
			String firstName = scanner.next();
			double hourlyRate = scanner.nextDouble();
			String duesVal = scanner.next();
			double dues = duesVal == "" ? 0.00 : Double.parseDouble(duesVal);
			String payMethodWithComma = scanner.nextLine();
			String delims = "[,]";
    		String[] tokens = payMethodWithComma.split(delims);
    		String payMethodSpace = tokens[1];
    		String payMethod = payMethodSpace.trim();
			
			if (payMethod.equals(directDeposit)) {
				HourlyEmployee hourly = new HourlyEmployee(employeeId, lastName, 
				firstName, hourlyRate, dues, new DirectDepositPayment(payMethod));
				hourlyEmployees.add(hourly);
			}
			else if (payMethod.equals(pickUp)) {
				HourlyEmployee hourly = new HourlyEmployee(employeeId, lastName, 
				firstName, hourlyRate, dues, new PickUpPayment(payMethod));
				hourlyEmployees.add(hourly);
			}
			else {
				HourlyEmployee hourly = new HourlyEmployee(employeeId, lastName, 
				firstName, hourlyRate, dues, new MailPayment(payMethod));
				hourlyEmployees.add(hourly);
			}
		}
		scanner.close();
    }
    
    public void readSalariedEmployees() throws FileNotFoundException {
    	Scanner scanner = new Scanner(new File("salaried_employees.csv"));
    	String directDeposit = "DD";
		String pickUp = "PU";
    	scanner.useDelimiter(",");
    	scanner.nextLine();
    	while (scanner.hasNext()) {
    		int employeeId = scanner.nextInt();
    		String lastName = scanner.next();
    		String firstName = scanner.next();
    		double salary = scanner.nextDouble();
    		double commission = scanner.nextDouble();
    		String duesVal = scanner.next();
			double dues = duesVal == "" ? 0.00 : Double.parseDouble(duesVal);
    		String payMethodWithComma = scanner.nextLine();
    		String delims = "[,]";
    		String[] tokens = payMethodWithComma.split(delims);
    		String payMethodSpace = tokens[1];
    		String payMethod = payMethodSpace.trim();
    		
    		if (payMethod.equals(directDeposit)) {
    			SalariedEmployee salaried = new SalariedEmployee(employeeId,
    		    lastName, firstName, salary, commission, dues, 
    		    new DirectDepositPayment(payMethod));
    		    salariedEmployees.add(salaried);
    		}
    		else if (payMethod.equals(pickUp)) {
    			SalariedEmployee salaried = new SalariedEmployee(employeeId,
    			lastName, firstName, salary, commission, dues, 
    	    	new PickUpPayment(payMethod));
    	    	salariedEmployees.add(salaried);
    		}
    		else {
    			SalariedEmployee salaried = new SalariedEmployee(employeeId,
    	    	lastName, firstName, salary, commission, dues, 
    	    	new MailPayment(payMethod));
    			salariedEmployees.add(salaried);
    		}
    	}
    	scanner.close();
    }
    
    public ArrayList<HourlyEmployee> getHourlyData() {
    	return hourlyEmployees;
    }
    
    public ArrayList<SalariedEmployee> getSalaryData() {
    	return salariedEmployees;
    }
    
    public String outputHourlyPay() throws ParseException, Exception {
    	String hourlyOutput = "";
    	for (HourlyEmployee h: hourlyEmployees) {
    		SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yy");
    		h.readTimesheet();
    		hourlyOutput += h.pay(f.parse("06-20-16"), f.parse("06-26-16")) + "\n";
    	}
    	return hourlyOutput;
    }
    
    public String outputSalariedPay() throws ParseException, Exception {
    	String salariedOutput = "";
    	for (SalariedEmployee s: salariedEmployees) {
    		SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yy");
    		s.readReceipts();
    		salariedOutput += s.pay(f.parse("06-20-2016"), f.parse("06-26-2016")) + "\n";
    	}
    	return salariedOutput;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.hourlyEmployees = new ArrayList<HourlyEmployee>();
		this.salariedEmployees = new ArrayList<SalariedEmployee>();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TitanPay Payroll");
		
		showRunPayroll();	
		
	}
	
	public void showRunPayroll() throws Exception {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RunPayroll.fxml"));
			runPayroll = (AnchorPane) loader.load();
			
			Scene scene = new Scene(runPayroll);
            primaryStage.setScene(scene);
            primaryStage.show();
			
			RunPayrollController controller = loader.getController();
			controller.setMainApp(this);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private static final String PERSISTENCE_UNIT_NAME = "TitanPay";
	private static EntityManagerFactory factory;
	
	public void Import() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query query1 = em.createQuery("select h from HourlyEmployee h");
		Query query2 = em.createQuery("select s from SalariedEmployee s");
		Query query3 = em.createQuery("select t from TimeCard t");
		Query query4 = em.createQuery("select r from Receipt r");
		List<HourlyEmployee> hourlyList = query1.getResultList();
		List<SalariedEmployee> salariedList = query2.getResultList();
		List<TimeCard> tcList = query3.getResultList();
		List<Receipt> receiptList = query4.getResultList();
		
		for (HourlyEmployee hourly : hourlyList) {
			System.out.println(hourly);
		}
		for (SalariedEmployee salaried : salariedList) {
			System.out.println(salaried);
		}
		for (TimeCard tc : tcList) {
			System.out.println(tc);
		}
		for (Receipt r : receiptList) {
			System.out.println(r);
		}
	}

	public static void main(String[] args) {
		
	}
}
