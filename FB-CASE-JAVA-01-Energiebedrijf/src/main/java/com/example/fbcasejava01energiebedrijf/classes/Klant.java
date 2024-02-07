package com.example.fbcasejava01energiebedrijf.classes;

public class Klant {

        private int klantnummer;
        private String voornaam;
        private String achternaam;
        private double jaarlijksVoorschot;

        public Klant(int klantnummer, String voornaam, String achternaam, double jaarlijksVoorschot) {
            this.klantnummer = klantnummer;
            this.voornaam = voornaam;
            this.achternaam = achternaam;
            this.jaarlijksVoorschot = jaarlijksVoorschot;
        }

        public int getKlantnummer() {
            return klantnummer;
        }

        public void setKlantnummer(int klantnummer) {
            this.klantnummer = klantnummer;
        }

        public String getVoornaam() {
            return voornaam;
        }

        public void setVoornaam(String voornaam) {
            this.voornaam = voornaam;
        }

        public String getAchternaam() {
            return achternaam;
        }

        public void setAchternaam(String achternaam) {
            this.achternaam = achternaam;
        }

        public double getJaarlijksVoorschot() {
            return jaarlijksVoorschot;
        }

        public void setJaarlijksVoorschot(double jaarlijksVoorschot) {
            this.jaarlijksVoorschot = jaarlijksVoorschot;
        }
}
