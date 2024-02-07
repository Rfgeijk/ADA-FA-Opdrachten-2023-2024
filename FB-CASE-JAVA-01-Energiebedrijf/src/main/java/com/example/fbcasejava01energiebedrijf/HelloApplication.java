package com.example.fbcasejava01energiebedrijf;

import com.example.fbcasejava01energiebedrijf.classes.DataEntry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private ArrayList<DataEntry> dataList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

        Label klant = new Label("Vul uw hier Klantgegevens in:");
        TextField txtcustomerNumber= new TextField("Klantnr");
        TextField txtfirstName = new TextField("Voornaam");
        TextField txtlastName = new TextField("Achternaam");
        TextField txtannualAdvance = new TextField("Jaarlijks voorschot");

        Label elektriciteit = new Label("Vul hier uw elektriciteitsgegevens met start en eind datum van de week:");
        TextField txtelectricityRate = new TextField("Tarief kWh");
        DatePicker electricityFromDate = new DatePicker();
        DatePicker electricityToDate = new DatePicker();

        Label gas = new Label("Vul hier uw gas gegevens met start en eind datum van de week:");
        TextField txtgasRate = new TextField("Tarief gas");
        DatePicker gasFromDate = new DatePicker();
        DatePicker gasToDate = new DatePicker();

        Label verbruik = new Label("Vul hier uw totale verbruik in met start en eind datum van de week:");
        TextField txtelectricityConsumption = new TextField("elektriciteit consumptie");
        TextField txtgasConsumption = new TextField("gas consumptie");
        DatePicker consumptionStartDate = new DatePicker();
        DatePicker consumptionEndDate = new DatePicker();

        Button registerButton = new Button("Register");

        root.add(klant, 0, 0);
        root.add(txtcustomerNumber, 0, 1);
        root.add(txtfirstName, 0, 2);
        root.add(txtlastName, 0, 3);
        root.add(txtannualAdvance, 0, 4);

        root.add(elektriciteit, 0, 5);
        root.add(txtelectricityRate, 0, 6);
        root.add(electricityFromDate, 0, 7);
        root.add(electricityToDate, 0, 8);

        root.add(gas, 0, 9);
        root.add(txtgasRate, 0, 10);
        root.add(gasFromDate, 0, 11);
        root.add(gasToDate, 0, 12);

        root.add(verbruik, 0, 13);
        root.add(txtelectricityConsumption, 0, 14);
        root.add(txtgasConsumption, 0, 15);
        root.add(consumptionStartDate, 0, 16);
        root.add(consumptionEndDate, 0, 17);

        root.add(registerButton, 0, 18);


        registerButton.setOnAction(e -> {
            // Gegevens ophalen uit de tekstvelden en datepickers
            String electricityRate = txtelectricityRate.getText();
            String electricityFrom = electricityFromDate.getValue().toString();
            String electricityTo = electricityToDate.getValue().toString();

            String customerNumber = txtcustomerNumber.getText();
            String firstName = txtfirstName.getText();
            String lastName = txtlastName.getText();
            String annualAdvance = txtannualAdvance.getText();

            String gasRate = txtgasRate.getText();
            String gasFrom = gasFromDate.getValue().toString();
            String gasTo = gasToDate.getValue().toString();

            String electricityConsumption = txtelectricityConsumption.getText();
            String gasConsumption = txtgasConsumption.getText();
            String consumptionStart = consumptionStartDate.getValue().toString();
            String consumptionEnd = consumptionEndDate.getValue().toString();

            System.out.println("Electricity Rate: " + electricityRate);
            System.out.println("Electricity From Date: " + electricityFrom);
            System.out.println("Electricity To Date: " + electricityTo);
            System.out.println("Customer Number: " + customerNumber);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Annual Advance: " + annualAdvance);
            System.out.println("Gas Rate: " + gasRate);
            System.out.println("Gas From Date: " + gasFrom);
            System.out.println("Gas To Date: " + gasTo);
            System.out.println("Electricity Consumption: " + electricityConsumption);
            System.out.println("Gas Consumption: " + gasConsumption);
            System.out.println("Consumption Start Date: " + consumptionStart);
            System.out.println("Consumption End Date: " + consumptionEnd);
        });



        Scene scene = new Scene(root, 650, 650);
        stage.setTitle("FB-CASE-JAVA-01-Energiebedrijf");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}