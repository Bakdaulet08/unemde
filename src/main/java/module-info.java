module com.example.newunemde {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.media;


    opens com.example.newunemde to javafx.fxml;
    exports com.example.newunemde;
}