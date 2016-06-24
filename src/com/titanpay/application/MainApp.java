package com.titanpay.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private AnchorPane runPayroll;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TitanPay Payroll");
		
		showRunPayroll();	
		
	}
	
	public void showRunPayroll() {
		
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(MainApp.class.getResource("view/RunPayroll.fxml"));
			runPayroll = (AnchorPane) loader.load();
			
			Scene scene = new Scene(runPayroll);
			primaryStage.setScene(scene);
			primaryStage.show();
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
