package com.example.carapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

/**
 * Kontroler obsługujący logikę interfejsu z pliku FXML.
 *
 * PAMIĘTAJ: Musisz dodać atrybut fx:controller do elementu głównego
 * w pliku FXML (np. <BorderPane fx:controller="CarAppController" ...>).
 */
public class CarAppController {

    // == Sekcja Samochód ==
    @FXML
    private TextField modelField; // fx:id="modelField"
    @FXML
    private TextField weightField; // fx:id="weightField"
    @FXML
    private TextField speedField; // fx:id="speedField"

    // == Sekcja Skrzynia Biegów ==
    @FXML
    private TextField gearField; // fx:id="gearField"

    // == Sekcja Mapa (wyświetlanie) ==
    @FXML
    private ImageView carImage; // fx:id="carImage"
    @FXML
    private Label mapLabel; // fx:id="mapLabel"


    /**
     * Metoda inicjalizująca kontroler po załadowaniu FXML.
     * Dobra do ustawienia początkowych wartości lub nasłuchiwaczy.
     */
    @FXML
    public void initialize() {
        // Przykład: Ustawienie początkowej wartości
        modelField.setText("Honda Civic");
        speedField.setText("0 km/h");
        gearField.setText("N");
    }

    // --- Obsługa zdarzeń z sekcji Samochód ---

    @FXML
    private void handleWlaczButton(ActionEvent event) {
        System.out.println("Samochód Włączony.");
        // Tutaj logika włączania silnika, np. zmiana koloru przycisku
    }

    @FXML
    private void handleWylaczButton(ActionEvent event) {
        System.out.println("Samochód Wyłączony.");
        // Tutaj logika wyłączania silnika
    }

    // --- Obsługa zdarzeń z sekcji Skrzynia Biegów ---

    @FXML
    private void handleZwiekszBieg(ActionEvent event) {
        try {
            int currentGear = Integer.parseInt(gearField.getText());
            currentGear++;
            gearField.setText(String.valueOf(currentGear));
            System.out.println("Zmieniono bieg na: " + currentGear);
        } catch (NumberFormatException e) {
            gearField.setText("1"); // Ustawienie na 1 w razie błędu
        }
    }

    @FXML
    private void handleZmniejszBieg(ActionEvent event) {
        try {
            int currentGear = Integer.parseInt(gearField.getText());
            if (currentGear > 0) {
                currentGear--;
            }
            gearField.setText(String.valueOf(currentGear));
            System.out.println("Zmieniono bieg na: " + currentGear);
        } catch (NumberFormatException e) {
            gearField.setText("N");
        }
    }

    // --- Obsługa zdarzeń z sekcji Silnik ---

    @FXML
    private void handleDodajGazu(ActionEvent event) {
        System.out.println("Dodano gazu!");
        // Tutaj logika zwiększania obrotów/prędkości
    }

    // --- Obsługa zdarzeń z sekcji Sprzęgło ---

    @FXML
    private void handleNacisnijSprzeglo(ActionEvent event) {
        // Przykładowa interakcja z innym elementem
        mapLabel.setText("Sprzęgło: WCIŚNIĘTE");
        System.out.println("Sprzęgło wciśnięte.");
    }
}