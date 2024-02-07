module com.example.fbcasejava01energiebedrijf {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fbcasejava01energiebedrijf to javafx.fxml;
    exports com.example.fbcasejava01energiebedrijf;
}