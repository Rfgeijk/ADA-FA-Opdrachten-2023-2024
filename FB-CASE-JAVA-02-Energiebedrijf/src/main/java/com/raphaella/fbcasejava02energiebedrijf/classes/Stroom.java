package com.raphaella.fbcasejava02energiebedrijf.classes;

public class Stroom {

    private String electricityRate;

    private String electricityFromDate;

    private String electricityToDate;

    public Stroom(String electricityRate, String electricityFromDate, String electricityToDate) {
        this.electricityRate = electricityRate;
        this.electricityFromDate = electricityFromDate;
        this.electricityToDate = electricityToDate;
    }

    public String getElectricityRate() {
        return electricityRate;
    }

    public void setElectricityRate(String electricityRate) {
        this.electricityRate = electricityRate;
    }

    public String getElectricityFromDate() {
        return electricityFromDate;
    }

    public void setElectricityFromDate(String electricityFromDate) {
        this.electricityFromDate = electricityFromDate;
    }

    public String getElectricityToDate() {
        return electricityToDate;
    }

    public void setElectricityToDate(String electricityToDate) {
        this.electricityToDate = electricityToDate;
    }
}

