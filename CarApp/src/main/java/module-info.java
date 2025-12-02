module com.example.carapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carapp to javafx.fxml;
    exports com.example.carapp;
}