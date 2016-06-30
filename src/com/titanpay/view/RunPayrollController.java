package com.titanpay.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import com.titanpay.MainApp;



public class RunPayrollController {
	
	@FXML
    private Button btRun;
    @FXML
    private Button btCancel;
    @FXML
    private RadioButton rbAll;
    @FXML
    private RadioButton rbHourly;
    @FXML
    private RadioButton rbSalaried;
    @FXML
    private TextArea taResults;
    @FXML
    private ComboBox<String> cbSelector;
    @FXML 
    private ScrollPane scrollPane;
    
    private MainApp mainApp;
    
    public RunPayrollController() {
    	
    }
    
    @FXML
    private void initialize() {
    	
    }
    
    public void setMainApp(MainApp mainApp) throws IOException {
    	this.mainApp = mainApp;
    	
    	mainApp.readHourlyEmployees();
    	mainApp.readSalariedEmployees();
    	mainApp.getHourlyData();
    	mainApp.getSalaryData();
 
    } 
    
    @FXML
    private void runPayroll() throws Exception {
    	
    	if (rbHourly.isSelected()) {
    		taResults.setText(mainApp.outputHourlyPay());
    	}
    	else if (rbSalaried.isSelected()) {
    		taResults.setText(mainApp.outputSalariedPay());
    	}
    	else {
    		taResults.setText(mainApp.outputHourlyPay());
    		taResults.setText(mainApp.outputSalariedPay());
    	}
    }
    
    @FXML
    private void cancel() {
    	Stage stage = (Stage) btCancel.getScene().getWindow();
    	stage.close();
    }
}
