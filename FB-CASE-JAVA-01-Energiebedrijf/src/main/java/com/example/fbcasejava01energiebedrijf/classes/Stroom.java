package com.example.fbcasejava01energiebedrijf.classes;

public class Stroom {
    private double tariefKwh;
    private String datumVanaf;
    private String datumTot;

    public Stroom(double tariefKwh, String datumVanaf, String datumTot) {
        this.tariefKwh = tariefKwh;
        this.datumVanaf = datumVanaf;
        this.datumTot = datumTot;
    }

    public double getTariefKwh() {
        return tariefKwh;
    }

    public void setTariefKwh(double tariefKwh) {
        this.tariefKwh = tariefKwh;
    }

    public String getDatumVanaf() {
        return datumVanaf;
    }

    public void setDatumVanaf(String datumVanaf) {
        this.datumVanaf = datumVanaf;
    }

    public String getDatumTot() {
        return datumTot;
    }

    public void setDatumTot(String datumTot) {
        this.datumTot = datumTot;
    }
}
