module com.raphaella.fbcasejava02energiebedrijf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.raphaella.fbcasejava02energiebedrijf to javafx.fxml;
    exports com.raphaella.fbcasejava02energiebedrijf;
}