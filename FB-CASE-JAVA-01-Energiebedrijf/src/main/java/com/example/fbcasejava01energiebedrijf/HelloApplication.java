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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HelloApplication extends Application {

    private double stroomTarief;
    private double gasTarief;
    private double totaalStroomVerbruik = 0;
    private double totaalGasVerbruik = 0;

    public class WeeklyUsage {
        private int weekNumber;
        private double stroomVerbruik;
        private double gasVerbruik;


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


    private List<WeeklyUsage> weeklyUsages = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();


        Label lblJaarvoorschot = new Label("Jaarlijks Voorschot:");
        Label lblPrijsgas = new Label("Gas Tarief:");
        Label lblPrijsstroom = new Label("Stroom Tarief:");
        Label lblVerbruikStroom = new Label("Wekelijks stroom verbruik (kWh):");
        Label lblVerbruikGas = new Label("Wekelijks gas verbruik (m3):");

        TextField txtJaarvoorschot = new TextField();
        TextField txtPrijsgas = new TextField();
        TextField txtPrijsstroom = new TextField();
        TextField txtVerbruikStroom = new TextField();
        TextField txtVerbruikGas = new TextField();


        Button btnSaveYearlyData = new Button("Jaarlijkse Gegevens Opslaan");
        btnSaveYearlyData.setOnAction(event -> saveYearlyData(txtJaarvoorschot.getText(), txtPrijsgas.getText(), txtPrijsstroom.getText()));

        Button btnSaveWeeklyUsage = new Button("Wekelijks Verbruik Opslaan");
        btnSaveWeeklyUsage.setOnAction(event -> {
            try {
                double stroomVerbruik = Double.parseDouble(txtVerbruikStroom.getText());
                double gasVerbruik = Double.parseDouble(txtVerbruikGas.getText());

                LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Amsterdam"));
                int weekNumber = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

                saveWeeklyUsage(String.valueOf(stroomVerbruik), String.valueOf(gasVerbruik), String.valueOf(weekNumber));
                addWeeklyUsage(stroomVerbruik, gasVerbruik);
            } catch (NumberFormatException e) {
                System.out.println("Fout: Ongeldige invoerwaarde voor stroom of gas.");
            }
        });


        Button btnShowOverview = new Button("Toon Overzicht");
        btnShowOverview.setOnAction(event -> showOverview());

        root.add(lblJaarvoorschot, 0, 0);
        root.add(txtJaarvoorschot, 1, 0);
        root.add(lblPrijsgas, 0, 1);
        root.add(txtPrijsgas, 1, 1);
        root.add(lblPrijsstroom, 0, 2);
        root.add(txtPrijsstroom, 1, 2);
        root.add(btnSaveYearlyData, 1, 3);

        root.add(lblVerbruikStroom, 0, 4);
        root.add(txtVerbruikStroom, 1, 4);
        root.add(lblVerbruikGas, 0, 5);
        root.add(txtVerbruikGas, 1, 5);
        root.add(btnSaveWeeklyUsage, 1, 6);
        root.add(btnShowOverview, 1, 7);


        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Energiebedrijf OOP 1");
        stage.setScene(scene);
        stage.show();
    }

    private void saveYearlyData(String jaarvoorschot, String prijsGas, String prijsStroom) {
        if (!jaarvoorschot.isEmpty() && !prijsGas.isEmpty() && !prijsStroom.isEmpty()) {
            double voorschot = Double.parseDouble(jaarvoorschot);
            gasTarief = Double.parseDouble(prijsGas);
            stroomTarief = Double.parseDouble(prijsStroom);

            // Tonen van opgeslagen jaarlijkse gegevens
            System.out.println("Jaarlijks Voorschot: " + voorschot);
            System.out.println("Gas Tarief: " + gasTarief);
            System.out.println("Stroom Tarief: " + stroomTarief);

            System.out.println("Jaarlijkse gegevens opgeslagen!");
        } else {
            System.out.println("Warning: Vul alle velden in.");
        }
    }


    private void saveWeeklyUsage(String verbruikStroom, String verbruikGas, String weekNumber) {
        if (!verbruikStroom.isEmpty() && !verbruikGas.isEmpty()) {
            try {
                double stroomVerbruik = Double.parseDouble(verbruikStroom);
                double gasVerbruik = Double.parseDouble(verbruikGas);


                System.out.println("Wekelijks Stroom Verbruik: " + stroomVerbruik);
                System.out.println("Wekelijks Gas Verbruik: " + gasVerbruik);


                int weekNumberInt = Integer.parseInt(weekNumber);
                WeeklyUsage weeklyUsage = new WeeklyUsage(weekNumberInt, stroomVerbruik, gasVerbruik);
                weeklyUsages.add(weeklyUsage);


                addWeeklyUsage(stroomVerbruik, gasVerbruik);

                System.out.println("Wekelijks verbruik opgeslagen!");
            } catch (NumberFormatException e) {
                System.out.println("Warning: Ongeldige invoerwaarde verbruik stroom of gas.");
            }
        } else {
            System.out.println("Warning: Vul alle velden in.");
        }
    }


    private void addWeeklyUsage(double stroomVerbruik, double gasVerbruik) {
        totaalStroomVerbruik += stroomVerbruik;
        totaalGasVerbruik += gasVerbruik;
    }

    private double calculateWeeklyUsage(int weekNumber) {
        double totalWeeklyUsage = 0;

        for (WeeklyUsage weeklyUsage : weeklyUsages) {
            if (weeklyUsage.getWeekNumber() == weekNumber) {
                totalWeeklyUsage += weeklyUsage.getStroomVerbruik() + weeklyUsage.getGasVerbruik();
            }
        }

        return totalWeeklyUsage;
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


    private void showWeeklyOverview(int weekNumber) {
        double weeklyUsage = calculateWeeklyUsage(weekNumber);
        System.out.println("Totaal Verbruik Week " + weekNumber + ": " + weeklyUsage);
    }


    private void showMonthlyOverview(int monthNumber) {
        double monthlyUsage = calculateMonthlyUsage(monthNumber);
        System.out.println("Totaal Verbruik Maand " + monthNumber + ": " + monthlyUsage);
    }


    private void showYearlyOverview() {
        double yearlyUsage = calculateYearlyUsage();
        System.out.println("Totaal Verbruik hele Jaar: " + yearlyUsage);
    }


    private void showOverview() {

        System.out.println("Totaal Stroom Verbruik: " + totaalStroomVerbruik);
        System.out.println("Totaal Gas Verbruik: " + totaalGasVerbruik);


        double totaleKosten = (totaalStroomVerbruik * stroomTarief) + (totaalGasVerbruik * gasTarief);
        System.out.println("Totale Kosten: " + totaleKosten);


        for (int i = 1; i <= 52; i++) {
            showWeeklyOverview(i);
        }

        for (int i = 1; i <= 12; i++) {
            showMonthlyOverview(i);
        }

        showYearlyOverview();
    }


    public static void main(String[] args) {
        launch();
    }
}
