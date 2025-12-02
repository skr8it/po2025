package com.example.carapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CarAppLauncher extends Application {

    private static final String FXML_PATH = "CarApp/src/main/resources/com/example/carapp/CarInterface.fxml"; // Zmień na nazwę swojego pliku FXML!

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Ładowanie pliku FXML
            // Upewnij się, że plik FXML znajduje się w odpowiednim miejscu (resources)
            Parent root = FXMLLoader.load(getClass().getResource("/CarInterface.fxml"));

            // 2. Konfiguracja sceny
            Scene scene = new Scene(root);

            // 3. Konfiguracja głównego okna (Stage)
            primaryStage.setTitle("Hello!"); // Tytuł zgodny z obrazkiem
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Błąd ładowania pliku FXML: " + FXML_PATH);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}