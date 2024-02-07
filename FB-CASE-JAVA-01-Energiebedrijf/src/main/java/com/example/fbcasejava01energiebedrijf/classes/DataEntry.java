package com.example.fbcasejava01energiebedrijf.classes;

public class DataEntry {

    private String type;  // Dit kan bijvoorbeeld zijn: "Klant", "Stroom", "Gas", "Verbruik"
    private Object data;  // Hier slaan we het specifieke object op (Klant, Stroom, Gas, Verbruik)

    public DataEntry(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

}
