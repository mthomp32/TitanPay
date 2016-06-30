package com.titanpay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.titanpay.accounting.DirectDepositPayment;
import com.titanpay.accounting.HourlyEmployee;
import com.titanpay.accounting.MailPayment;
import com.titanpay.accounting.PickUpPayment;
import com.titanpay.accounting.SalariedEmployee;
import com.titanpay.view.RunPayrollController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private AnchorPane runPayroll;
	private ArrayList<SalariedEmployee> salariedEmployees;
    private ArrayList<HourlyEmployee> hourlyEmployees;
    
    public void readHourlyEmployees() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("hourly_employees.csv"));
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			scanner.nextLine();
			scanner.nextLine();
			int employeeId = scanner.nextInt();
			String lastName = scanner.next();
			String firstName = scanner.next();
			double hourlyRate = scanner.nextDouble();
			double dues = scanner.nextDouble();
			String payMethod = scanner.next();
			
			if (payMethod == "DD") {
				HourlyEmployee hourly = new HourlyEmployee(employeeId, lastName, 
				firstName, hourlyRate, dues, new DirectDepositPayment(payMethod));
				hourlyEmployees.add(hourly);
			}
			else if (payMethod == "PU") {
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
    	scanner.useDelimiter(",");
    	while (scanner.hasNext()) {
    		scanner.nextLine();
    		scanner.nextLine();
    		int employeeId = scanner.nextInt();
    		String lastName = scanner.next();
    		String firstName = scanner.next();
    		double salary = scanner.nextDouble();
    		double commission = scanner.nextDouble();
    		double dues = scanner.nextDouble();
    		String payMethod = scanner.next();
    		
    		if (payMethod == "DD") {
    			SalariedEmployee salaried = new SalariedEmployee(employeeId,
    		    lastName, firstName, salary, commission, dues, 
    		    new DirectDepositPayment(payMethod));
    		    salariedEmployees.add(salaried);
    		}
    		else if (payMethod == "PU") {
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
    		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
    		h.readTimesheet();
    		hourlyOutput += h.pay(f.parse("2015-06-20"), f.parse("2015-06-26")) + "\n";
    	}
    	return hourlyOutput;
    }
    
    public String outputSalariedPay() throws ParseException, Exception {
    	String salariedOutput = "";
    	for (SalariedEmployee s: salariedEmployees) {
    		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
    		salariedOutput += s.pay(f.parse("2015-06-20"), f.parse("2015-06-26")) + "\n";
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

	public static void main(String[] args) {
		launch(args);
	}
}
