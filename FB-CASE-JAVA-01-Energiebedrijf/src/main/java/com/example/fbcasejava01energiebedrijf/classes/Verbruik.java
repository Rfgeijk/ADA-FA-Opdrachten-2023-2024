package com.example.fbcasejava01energiebedrijf.classes;

public class Verbruik {

    private String electricityConsumption;

    private String gasConsumption;

    private String consumptionStartDate;

    private String consumptionEndDate;

    public Verbruik(String electricityConsumption, String gasConsumption, String consumptionStartDate, String consumptionEndDate) {
        this.electricityConsumption = electricityConsumption;
        this.gasConsumption = gasConsumption;
        this.consumptionStartDate = consumptionStartDate;
        this.consumptionEndDate = consumptionEndDate;
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
}
