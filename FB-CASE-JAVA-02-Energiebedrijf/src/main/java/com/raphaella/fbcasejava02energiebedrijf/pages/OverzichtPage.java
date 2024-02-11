package com.raphaella.fbcasejava02energiebedrijf.pages;

import com.raphaella.fbcasejava02energiebedrijf.classes.Database;
import com.raphaella.fbcasejava02energiebedrijf.classes.Verbruik;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class OverzichtPage {

    private Database db = new Database();

    private Stage deStage = new Stage();

    private VBox root = new VBox();

    private Scene deScene = new Scene(root, 800, 600);

    private double totalMonthlyGasConsumption = 0.0;
    private double totalYearlyGasConsumption = 0.0;
    private double totalMonthlyElectricityConsumption = 0.0;
    private double totalYearlyElectricityConsumption = 0.0;


    public OverzichtPage() {
        loadConsumption();
    }

    private void loadConsumption() {
        List<Verbruik> consumption = db.getAllConsumption();


        VBox consumptionBox = null;
        Button updateButton = null;
        Button deleteButton = null;
        VBox consumptionContainer = null;
        for (Verbruik verbruik : consumption) {
            consumptionContainer = new VBox();
            consumptionBox = verbruik.show();

            updateButton = new Button("Update");
            updateButton.setOnAction(event -> {
                UpdatePage updatePage = new UpdatePage();
                updatePage.show();
            });

            deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> {
                db.deleteConsumption(verbruik);
            });

            // Calculations for monthly and yearly gas consumption
            double weeklyGasConsumption = Double.parseDouble(verbruik.getGasConsumption()) / 7;
            double monthlyGasConsumption = weeklyGasConsumption * 4;
            double yearlyGasConsumption = weeklyGasConsumption * 52;

            // Calculations for monthly and yearly electricity consumption
            double weeklyElectricityConsumption = Double.parseDouble(verbruik.getElectricityConsumption()) / 7;
            double monthlyElectricityConsumption = weeklyElectricityConsumption * 4;
            double yearlyElectricityConsumption = weeklyElectricityConsumption * 52;

            // Add to the total consumption
            totalMonthlyGasConsumption += monthlyGasConsumption;
            totalYearlyGasConsumption += yearlyGasConsumption;
            totalMonthlyElectricityConsumption += monthlyElectricityConsumption;
            totalYearlyElectricityConsumption += yearlyElectricityConsumption;
        }

        // Display total consumption in labels
        Label totalMonthlyGasLabel = new Label("Total Monthly Gas Consumption: " + totalMonthlyGasConsumption);
        Label totalYearlyGasLabel = new Label("Total Yearly Gas Consumption: " + totalYearlyGasConsumption);
        Label totalMonthlyElectricityLabel = new Label("Total Monthly Electricity Consumption: " + totalMonthlyElectricityConsumption);
        Label totalYearlyElectricityLabel = new Label("Total Yearly Electricity Consumption: " + totalYearlyElectricityConsumption);

        consumptionContainer.getChildren().addAll(
                consumptionBox, updateButton, deleteButton,
                totalMonthlyGasLabel, totalYearlyGasLabel,
                totalMonthlyElectricityLabel, totalYearlyElectricityLabel
        );

        root.getChildren().add(
                consumptionContainer
        );

    }

    public void show() {
        deStage.setScene(deScene);
        deStage.show();
    }

}
