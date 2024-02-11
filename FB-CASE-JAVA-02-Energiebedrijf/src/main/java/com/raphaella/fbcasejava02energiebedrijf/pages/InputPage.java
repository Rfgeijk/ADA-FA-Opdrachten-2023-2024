package com.raphaella.fbcasejava02energiebedrijf.pages;

import com.raphaella.fbcasejava02energiebedrijf.classes.Database;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InputPage {
    private Stage deStage = new Stage();
    private GridPane root = new GridPane();

    private Scene deScene = new Scene(root, 800, 600);

    public InputPage() {

        root.setHgap(10);
        root.setVgap(10);

        Label klant = new Label("Vul uw hier Klantgegevens in:");
        TextField txtcustomerNumber= new TextField();
        txtcustomerNumber.setPromptText("Klantnummer");
        TextField txtfirstName = new TextField();
        txtfirstName.setPromptText("Voornaam");
        TextField txtlastName = new TextField();
        txtlastName.setPromptText("Achternaam");


        Label verbruik = new Label("Vul hier uw totale verbruik in met start en eind datum van de week:");
        TextField txtelectricityConsumption = new TextField();
        txtelectricityConsumption.setPromptText("elektriciteit consumptie");
        TextField txtgasConsumption = new TextField();
        txtgasConsumption.setPromptText("gas consumptie");
        DatePicker consumptionStartDate = new DatePicker();
        DatePicker consumptionEndDate = new DatePicker();

        Button registerButton = new Button("Register");
        Button overzichtbutton = new Button("Hier kunt u de records van vorige week vinden en het totaal");

        root.add(klant, 0, 0);
        root.add(txtcustomerNumber, 0, 1);
        root.add(txtfirstName, 0, 2);
        root.add(txtlastName, 0, 3);


        root.add(verbruik, 0, 4);
        root.add(txtelectricityConsumption, 0, 5);
        root.add(txtgasConsumption, 0, 6);
        root.add(consumptionStartDate, 0, 7);
        root.add(consumptionEndDate, 0, 8);

        root.add(registerButton, 0, 9);
        root.add(overzichtbutton, 0, 10);


        registerButton.setOnAction(e -> {


            String customerNumber = txtcustomerNumber.getText();
            String firstName = txtfirstName.getText();
            String lastName = txtlastName.getText();


            String electricityConsumption = txtelectricityConsumption.getText();
            String gasConsumption = txtgasConsumption.getText();
            String consumptionStart = consumptionStartDate.getValue().toString();
            String consumptionEnd = consumptionEndDate.getValue().toString();

            if (txtcustomerNumber.getText().isEmpty() || txtfirstName.getText().isEmpty() ||
                    txtlastName.getText().isEmpty() ||
                    txtelectricityConsumption.getText().isEmpty() || txtgasConsumption.getText().isEmpty() ||
                    consumptionStartDate.getValue() == null || consumptionEndDate.getValue() == null) {

                System.out.println("Error: Please fill in all required fields.");
                return;
            }


            try (Connection connection = Database.getConnection()) {
                String insertQuery = "INSERT INTO Customer (customerNumber, firstName, lastName, electricityConsumption, gasConsumption, consumptionStart, consumptionEnd) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, customerNumber);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, electricityConsumption);
                    preparedStatement.setString(5, gasConsumption);
                    preparedStatement.setString(6, consumptionStart);
                    preparedStatement.setString(7, consumptionEnd);

                    preparedStatement.executeUpdate();
                    System.out.println("Data successfully inserted into the database.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }




        });

        overzichtbutton.setOnAction(e ->{
            OverzichtPage overzicht = new OverzichtPage();
            overzicht.show();
        });

    }

    public void show() {
        deStage.setScene(deScene);
        deStage.show();
    }
}

