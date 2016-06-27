package com.titanpay;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.titanpay.accounting.DirectDepositPayment;
import com.titanpay.accounting.HourlyEmployee;
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
    
    public void readHourlyEmployees() throws Exception {
		Scanner scanner = new Scanner(new File("hourly_employees.csv"));
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			HourlyEmployee hourly = new HourlyEmployee(scanner.nextInt(), scanner.next(), 
			scanner.next(), scanner.nextDouble(), scanner.nextDouble(), 
			new DirectDepositPayment(scanner.next()));
			hourlyEmployees.add(hourly);
		}
		scanner.close();
    }
    
    public void readSalariedEmployees() throws Exception {
    	Scanner scanner = new Scanner(new File("salaried_employees.csv"));
    	scanner.useDelimiter(",");
    	while (scanner.hasNext()) {
    		SalariedEmployee salaried = new SalariedEmployee(scanner.nextInt(),
    		scanner.next(), scanner.next(), scanner.nextDouble(), scanner.nextDouble(),
    		scanner.nextDouble(), new DirectDepositPayment(scanner.next()));
    		salariedEmployees.add(salaried);
    	}
    	scanner.close();
    }
    
    public ArrayList<HourlyEmployee> getHourlyData() {
    	return hourlyEmployees;
    }
    
    public ArrayList<SalariedEmployee> getSalaryData() {
    	return salariedEmployees;
    }
    
    public String outputHourlyPay() throws Exception {
    	String hourlyOutput = "";
    	for (HourlyEmployee h: hourlyEmployees) {
    		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
    		h.readTimesheet();
    		hourlyOutput += h.pay(f.parse("2015-06-20"), f.parse("2015-06-26")) + "\n";
    	}
    	return hourlyOutput;
    }
    
    public String outputSalariedPay() throws Exception {
    	String salariedOutput = "";
    	for (SalariedEmployee s: salariedEmployees) {
    		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
    		salariedOutput += s.pay(f.parse("2015-06-20"), f.parse("2015-06-26")) + "\n";
    	}
    	return salariedOutput;
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TitanPay Payroll");
		
		showRunPayroll();	
		
	}
	
	public void showRunPayroll() {
		
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
