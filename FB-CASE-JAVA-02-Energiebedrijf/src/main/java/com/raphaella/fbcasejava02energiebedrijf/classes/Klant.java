package com.raphaella.fbcasejava02energiebedrijf.classes;

public class Klant {

    private String customerNumber;

    private String firstName;

    private String lastName;

    private String annualAdvance;

    public Klant(String customerNumber, String firstName, String lastName, String annualAdvance) {
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

    public String getAnnualAdvance() {
        return annualAdvance;
    }

    public void setAnnualAdvance(String annualAdvance) {
        this.annualAdvance = annualAdvance;
    }
}

