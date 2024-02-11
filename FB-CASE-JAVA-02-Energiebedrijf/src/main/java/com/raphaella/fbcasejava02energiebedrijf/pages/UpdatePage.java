package com.raphaella.fbcasejava02energiebedrijf.pages;

import com.raphaella.fbcasejava02energiebedrijf.classes.Database;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class UpdatePage {
    private Stage deStage = new Stage();
    private GridPane root = new GridPane();

    private Scene deScene = new Scene(root, 800, 600);
    Database db = new Database();

    public UpdatePage() {

        root.setHgap(10);
        root.setVgap(10);

        Label klant = new Label("Vul uw hier Klantgegevens in:");
        TextField txtcustomerNumber = new TextField();
        txtcustomerNumber.setPromptText("Klantnummer");


        Label verbruik = new Label("Vul hier uw totale verbruik in met start en eind datum van de week:");
        TextField txtelectricityConsumption = new TextField();
        txtelectricityConsumption.setPromptText("elektriciteit consumptie");
        TextField txtgasConsumption = new TextField();
        txtgasConsumption.setPromptText("gas consumptie");
        DatePicker consumptionStartDate = new DatePicker();
        DatePicker consumptionEndDate = new DatePicker();

        Button updateButton = new Button("Update");

        root.add(klant, 0, 0);
        root.add(txtcustomerNumber, 0, 1);

        root.add(verbruik, 0, 2);
        root.add(txtelectricityConsumption, 0, 3);
        root.add(txtgasConsumption, 0, 4);
        root.add(consumptionStartDate, 0, 5);
        root.add(consumptionEndDate, 0, 6);

        root.add(updateButton, 0, 7);


        updateButton.setOnAction(e -> {
            String currentCustomerNumber = txtcustomerNumber.getText();
            String newElectricityConsumption = txtelectricityConsumption.getText();
            String newGasConsumption = txtgasConsumption.getText();
            String newConsumptionStart = consumptionStartDate.getValue().toString();
            String newConsumptionEnd = consumptionEndDate.getValue().toString();

            db.updateConsumption(currentCustomerNumber, newElectricityConsumption, newGasConsumption, newConsumptionStart, newConsumptionEnd);
        });
    }

    public void show() {
        deStage.setScene(deScene);
        deStage.show();
    }
}
