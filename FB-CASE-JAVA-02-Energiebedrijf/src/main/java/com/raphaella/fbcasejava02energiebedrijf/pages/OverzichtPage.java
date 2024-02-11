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

    public OverzichtPage() {
        loadConsumption();
    }

    private void loadConsumption() {
        List<Verbruik> consumption = db.getAllConsumption();

        for (Verbruik verbruik : consumption) {
            VBox consumptionBox = verbruik.show();

            Button updateButton = new Button("Update");
            updateButton.setOnAction(event -> {
                UpdatePage updatePage = new UpdatePage();
                updatePage.show();
            });

            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> {
                db.deleteConsumption(verbruik); // Pass the specific Verbruik object to delete
            });


            // Calculations for monthly and yearly gas consumption
            double weeklyGasConsumption = Double.parseDouble(verbruik.getGasConsumption()) / 7;
            double monthlyGasConsumption = weeklyGasConsumption * 4;
            double yearlyGasConsumption = weeklyGasConsumption * 52;

            // Calculations for monthly and yearly electricity consumption
            double weeklyElectricityConsumption = Double.parseDouble(verbruik.getElectricityConsumption()) / 7;
            double monthlyElectricityConsumption = weeklyElectricityConsumption * 4;
            double yearlyElectricityConsumption = weeklyElectricityConsumption * 52;

            // Displaying calculated values in labels
            Label monthlyGasLabel = new Label("Monthly Gas Consumption: " + monthlyGasConsumption);
            Label yearlyGasLabel = new Label("Yearly Gas Consumption: " + yearlyGasConsumption);

            Label monthlyElectricityLabel = new Label("Monthly Electricity Consumption: " + monthlyElectricityConsumption);
            Label yearlyElectricityLabel = new Label("Yearly Electricity Consumption: " + yearlyElectricityConsumption);

            root.getChildren().addAll(
                    consumptionBox, updateButton, deleteButton,
                    monthlyGasLabel, yearlyGasLabel,
                    monthlyElectricityLabel, yearlyElectricityLabel
            );

        }
    }


    public void show() {
        deStage.setScene(deScene);
        deStage.show();
    }

}
