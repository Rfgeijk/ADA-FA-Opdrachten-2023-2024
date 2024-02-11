package com.raphaella.fbcasejava02energiebedrijf.classes;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.Date;

public class Verbruik {

    private String electricityConsumption;

    private String gasConsumption;

    private String consumptionStartDate;

    private String consumptionEndDate;

    private String customerNumber;

    private String firstName;

    private String lastName;

    private String annualAdvance;



    public Verbruik(String electricityConsumption, String gasConsumption, Date consumptionStartDate, Date consumptionEndDate) {
        this.electricityConsumption = electricityConsumption;
        this.gasConsumption = gasConsumption;
        this.consumptionStartDate = String.valueOf(consumptionStartDate);
        this.consumptionEndDate = String.valueOf(consumptionEndDate);
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualAdvance = annualAdvance;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(String electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public String getGasConsumption() {
        return gasConsumption;
    }

    public void setGasConsumption(String gasConsumption) {
        this.gasConsumption = gasConsumption;
    }

    public String getConsumptionStartDate() {
        return consumptionStartDate;
    }

    public void setConsumptionStartDate(String consumptionStartDate) {
        this.consumptionStartDate = consumptionStartDate;
    }

    public String getConsumptionEndDate() {
        return consumptionEndDate;
    }

    public void setConsumptionEndDate(String consumptionEndDate) {
        this.consumptionEndDate = consumptionEndDate;
    }

    public VBox show(){
        VBox listItem = new VBox();
        listItem.setPrefHeight( 100);
        listItem.setStyle("-fx-background-color: #aba6a6;");


        Label verbruik = new Label(electricityConsumption + " " + gasConsumption + " " + consumptionStartDate + consumptionEndDate);
        listItem.getChildren().add(verbruik);

        return listItem;
    }


}

