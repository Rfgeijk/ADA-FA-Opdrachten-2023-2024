package com.example.fbcasejava01energiebedrijf.classes;

public class Gas {

    private String gasRate;

    private String gasFromDate;

    private String gasToDate;

    public Gas(String gasRate, String gasFromDate, String gasToDate) {
        this.gasRate = gasRate;
        this.gasFromDate = gasFromDate;
        this.gasToDate = gasToDate;
    }

    public String getGasRate() {
        return gasRate;
    }

    public void setGasRate(String gasRate) {
        this.gasRate = gasRate;
    }

    public String getGasFromDate() {
        return gasFromDate;
    }

    public void setGasFromDate(String gasFromDate) {
        this.gasFromDate = gasFromDate;
    }

    public String getGasToDate() {
        return gasToDate;
    }

    public void setGasToDate(String gasToDate) {
        this.gasToDate = gasToDate;
    }

}
