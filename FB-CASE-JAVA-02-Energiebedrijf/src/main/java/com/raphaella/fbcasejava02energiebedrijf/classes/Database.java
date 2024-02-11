package com.raphaella.fbcasejava02energiebedrijf.classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/energiebedrijf";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public List<Verbruik> getAllConsumption() {
        List<Verbruik> consumptions = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/energiebedrijf", "root", "");
             Statement statement = connection.createStatement()) {


            String query = "SELECT electricityConsumption, gasConsumption, consumptionStart, consumptionEnd FROM customer";
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                String electricityConsumption = resultSet.getString("electricityConsumption");
                String gasConsumption = resultSet.getString("gasConsumption");
                Date consumptionStart = resultSet.getDate("consumptionStart");
                Date consumptionEnd = resultSet.getDate("consumptionEnd");

                Verbruik consumption = new Verbruik(electricityConsumption, gasConsumption, consumptionStart, consumptionEnd);
                consumptions.add(consumption);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return consumptions;
    }

    public void deleteConsumption(Verbruik consumption) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM customer WHERE electricityConsumption = ? AND gasConsumption = ? AND consumptionStart = ? AND consumptionEnd = ?")) {

            preparedStatement.setString(1, consumption.getElectricityConsumption());
            preparedStatement.setString(2, consumption.getGasConsumption());
            preparedStatement.setString(3, consumption.getConsumptionStartDate());
            preparedStatement.setString(4, consumption.getConsumptionEndDate());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Consumption deleted successfully.");
            } else {
                System.out.println("No matching consumption found for deletion.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateConsumption(String currentCustomerNumber, String newElectricityConsumption, String newGasConsumption, String newConsumptionStart, String newConsumptionEnd) {
        String selectQuery = "SELECT * FROM customer WHERE customerNumber=?";
        try (Connection connection = getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setString(1, currentCustomerNumber);
            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                String currentElectricityConsumption = resultSet.getString("electricityConsumption");
                String currentGasConsumption = resultSet.getString("gasConsumption");
                String currentConsumptionStart = resultSet.getString("consumptionStart");
                String currentConsumptionEnd = resultSet.getString("consumptionEnd");

                if (!newElectricityConsumption.equals(currentElectricityConsumption)
                        || !newGasConsumption.equals(currentGasConsumption)
                        || !newConsumptionStart.equals(currentConsumptionStart)
                        || !newConsumptionEnd.equals(currentConsumptionEnd)) {

                    String updateQuery = "UPDATE customer SET electricityConsumption=?, gasConsumption=?, consumptionStart=?, consumptionEnd=? WHERE customerNumber=?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, newElectricityConsumption);
                        updateStmt.setString(2, newGasConsumption);
                        updateStmt.setString(3, newConsumptionStart);
                        updateStmt.setString(4, newConsumptionEnd);
                        updateStmt.setString(5, currentCustomerNumber);

                        int affectedRows = updateStmt.executeUpdate();
                        if (affectedRows > 0) {
                            System.out.println("Consumption details updated successfully!");
                        } else {
                            System.out.println("Failed to update consumption details.");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("No changes detected. Consumption details remain the same.");
                }
            } else {
                System.out.println("Customer not found with customer number: " + currentCustomerNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
