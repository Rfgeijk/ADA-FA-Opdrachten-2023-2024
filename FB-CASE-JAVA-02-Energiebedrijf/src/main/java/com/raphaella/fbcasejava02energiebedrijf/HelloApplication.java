package com.raphaella.fbcasejava02energiebedrijf;

import com.raphaella.fbcasejava02energiebedrijf.classes.Database;
import com.raphaella.fbcasejava02energiebedrijf.pages.InputPage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Voorschot Applicatie");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Labels
        Label jaarlijksVoorschotLabel = new Label("Jaarlijks Voorschot:");
        GridPane.setConstraints(jaarlijksVoorschotLabel, 0, 0);

        Label gasTariefLabel = new Label("Gas Tarief:");
        GridPane.setConstraints(gasTariefLabel, 0, 1);

        Label stroomTariefLabel = new Label("Stroom Tarief:");
        GridPane.setConstraints(stroomTariefLabel, 0, 2);

        // TextFields
        TextField txtjaarlijksVoorschotInput = new TextField();
        GridPane.setConstraints(txtjaarlijksVoorschotInput, 1, 0);

        TextField txtgasTariefInput = new TextField();
        GridPane.setConstraints(txtgasTariefInput, 1, 1);

        TextField txtstroomTariefInput = new TextField();
        GridPane.setConstraints(txtstroomTariefInput, 1, 2);

        Button submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton,1,3);

        grid.getChildren().addAll(
                jaarlijksVoorschotLabel, txtjaarlijksVoorschotInput,
                gasTariefLabel, txtgasTariefInput,
                stroomTariefLabel, txtstroomTariefInput, submitButton
        );

        submitButton.setOnAction(e -> {
            String jaarlijksvoorschot = txtjaarlijksVoorschotInput.getText();
            String gastariefinput = txtgasTariefInput.getText();
            String stroomtariefInput = txtstroomTariefInput.getText();

            try (Connection connection = Database.getConnection()) {

                String insertQuery = "INSERT INTO Tariff (jaarlijksVoorschot, gasTarief, stroomTarief) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, jaarlijksvoorschot);
                    preparedStatement.setString(2, gastariefinput);
                    preparedStatement.setString(3, stroomtariefInput);

                    preparedStatement.executeUpdate();
                    System.out.println("Data successfully inserted into the database.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            if(jaarlijksvoorschot.isEmpty() || gastariefinput.isEmpty() || stroomtariefInput.isEmpty()) {
                System.out.println("Fout. Vul alle velden in.");
            } else {
                InputPage inputPage = new InputPage();
                inputPage.show();
            }
        });


        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }


}
