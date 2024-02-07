package com.example.fbcasejava01energiebedrijf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private static ArrayList<WeeklyUsage> weeklyUsages = new ArrayList<>();
    private static double totaalStroomVerbruik = 0;
    private static double totaalGasVerbruik = 0;
    private static double stroomTarief = 0;
    private static double gasTarief = 0;
    private static double jaarlijksVoorschot = 0;


    private static void addWeeklyUsage(double stroomVerbruik, double gasVerbruik) {
        totaalStroomVerbruik += stroomVerbruik;
        totaalGasVerbruik += gasVerbruik;

        // Voeg wekelijkse gegevens toe aan de lijst
        WeeklyUsage weeklyUsage = new WeeklyUsage(weeklyUsages.size() + 1, stroomVerbruik, gasVerbruik);
        weeklyUsages.add(weeklyUsage);

        // Toon het totaaloverzicht na het toevoegen van wekelijkse gegevens
        showOverview();
    }

    private static void showOverview() {
        // Tonen van totaal stroom- en gasverbruik
        System.out.println("Totaal Stroom Verbruik: " + totaalStroomVerbruik);
        System.out.println("Totaal Gas Verbruik: " + totaalGasVerbruik);

        // Berekenen en tonen van totale kosten
        double totaleKosten = (totaalStroomVerbruik * stroomTarief) + (totaalGasVerbruik * gasTarief);
        System.out.println("Totale Kosten: " + totaleKosten);
    }

    // Innerlijke klasse voor wekelijkse gegevens
    private static class WeeklyUsage {
        private final int weekNumber;
        private final double stroomVerbruik;
        private final double gasVerbruik;

        public WeeklyUsage(int weekNumber, double stroomVerbruik, double gasVerbruik) {
            this.weekNumber = weekNumber;
            this.stroomVerbruik = stroomVerbruik;
            this.gasVerbruik = gasVerbruik;
        }

        public int getWeekNumber() {
            return weekNumber;
        }

        public double getStroomVerbruik() {
            return stroomVerbruik;
        }

        public double getGasVerbruik() {
            return gasVerbruik;
        }
    }



    private double calculateMonthlyUsage(int monthNumber) {
        double totalMonthlyUsage = 0;

        for (WeeklyUsage weeklyUsage : weeklyUsages) {
            if ((weeklyUsage.getWeekNumber() - 1) / 4 + 1 == monthNumber) {
                totalMonthlyUsage += weeklyUsage.getStroomVerbruik() + weeklyUsage.getGasVerbruik();
            }
        }

        return totalMonthlyUsage;
    }

    private double calculateYearlyUsage() {
        double totalYearlyUsage = 0;

        for (WeeklyUsage weeklyUsage : weeklyUsages) {
            totalYearlyUsage += weeklyUsage.getStroomVerbruik() + weeklyUsage.getGasVerbruik();
        }

        return totalYearlyUsage;
    }





    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        TextField txtAnnualAdvance = new TextField("Jaarlijks voorschot");

        Button initializeButton = new Button("Initialize");
        initializeButton.setOnAction(e -> {
            jaarlijksVoorschot = Double.parseDouble(txtAnnualAdvance.getText());

            // Vraag gebruiker om tarieven in te voeren
            TextInputDialog gasTariefDialog = new TextInputDialog("0");
            gasTariefDialog.setHeaderText("Voer het gas tarief in:");
            gasTariefDialog.setContentText("Gas Tarief:");
            gasTarief = Double.parseDouble(gasTariefDialog.showAndWait().orElse("0"));

            TextInputDialog stroomTariefDialog = new TextInputDialog("0");
            stroomTariefDialog.setHeaderText("Voer het stroom tarief in:");
            stroomTariefDialog.setContentText("Stroom Tarief:");
            stroomTarief = Double.parseDouble(stroomTariefDialog.showAndWait().orElse("0"));

            System.out.println("Initialisatie voltooid. Voorschot: " + jaarlijksVoorschot +
                    ", Gas Tarief: " + gasTarief +
                    ", Stroom Tarief: " + stroomTarief);
        });



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



            // Bereken wekelijkse consumptie

            // Convert consumptions to doubles
            double electricityConsumptionValue = Double.parseDouble(electricityConsumption);
            double gasConsumptionValue = Double.parseDouble(gasConsumption);

            // Calculate weekly, monthly, and yearly consumption
            double weeklyElectricityConsumption = electricityConsumptionValue / 7;
            double monthlyElectricityConsumption = electricityConsumptionValue * 4;
            double yearlyElectricityConsumption = electricityConsumptionValue * 52;

            double weeklyGasConsumption = gasConsumptionValue / 7;
            double monthlyGasConsumption = gasConsumptionValue * 4;
            double yearlyGasConsumption = gasConsumptionValue * 52;

            // Add weekly usage to the total
            addWeeklyUsage(weeklyElectricityConsumption, weeklyGasConsumption);

            // Display the results
            System.out.println("Weekly Electricity Consumption: " + weeklyElectricityConsumption);
            System.out.println("Monthly Electricity Consumption: " + calculateMonthlyUsage(1));  // Use month 1 as an example
            System.out.println("Yearly Electricity Consumption: " + calculateYearlyUsage());
            System.out.println("Weekly Gas Consumption: " + weeklyGasConsumption);
            System.out.println("Monthly Gas Consumption: " + calculateMonthlyUsage(1));  // Use month 1 as an example
            System.out.println("Yearly Gas Consumption: " + calculateYearlyUsage());

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