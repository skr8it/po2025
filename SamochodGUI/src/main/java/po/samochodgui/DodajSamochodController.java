package po.samochodgui;

import auto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DodajSamochodController {

    @FXML private TextField modelTextField, registrationTextField, speedTextField;
    @FXML private TextField engineNameField, engineModelField, engineProducerField, engineWeightField, enginePriceField, engineMaxRevField;
    @FXML private TextField gearboxNameField, gearboxModelField, gearboxProducerField, gearboxWeightField, gearboxPriceField, gearsCountField;
    @FXML private TextField clutchNameField, clutchModelField, clutchProducerField, clutchWeightField, clutchPriceField;

    @FXML private ComboBox<Silnik> engineComboBox;
    @FXML private ComboBox<Skrzynia> gearboxComboBox;
    @FXML private ComboBox<Sprzeglo> clutchComboBox;
    @FXML private Button confirmButton;

    private SamochodController parentController;

    public void setParentController(SamochodController pc) { this.parentController = pc; }

    @FXML
    public void initialize() {
        setupData();
        setupListeners();
        setupConverters();
    }

    private void setupData() {
        // Silniki
        ObservableList<Silnik> silniki = FXCollections.observableArrayList(
                new Silnik("Pusty", 0, 0, "", "", 5000),
                new Silnik("1.9 TDI", 180, 5000, "STD", "VW", 5000),
                new Silnik("2.0 TSI", 150, 8000, "TSI", "Audi", 7000)
        );
        engineComboBox.setItems(silniki);

        // Sprzęgła
        ObservableList<Sprzeglo> sprzegla = FXCollections.observableArrayList(
                new Sprzeglo("Puste", 0, 0, "", ""),
                new Sprzeglo("Sachs Sport", 10, 1200, "S1", "Sachs"),
                new Sprzeglo("Valeo Comfort", 12, 800, "V1", "Valeo")
        );
        clutchComboBox.setItems(sprzegla);

        // Skrzynie
        ObservableList<Skrzynia> skrzynie = FXCollections.observableArrayList(
                new Skrzynia("Pusta", 0, 0, "", "", sprzegla.get(0), 0),
                new Skrzynia("ZF Manual", 50, 3000, "M6", "ZF", sprzegla.get(1), 6),
                new Skrzynia("DSG Automat", 80, 7000, "DQ", "VW", sprzegla.get(2), 7)
        );
        gearboxComboBox.setItems(skrzynie);
    }

    private void setupListeners() {
        engineComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) fillFields(newV, engineNameField, engineModelField, engineProducerField, engineWeightField, enginePriceField, engineMaxRevField, String.valueOf(newV.getMaxObroty()));
        });
        clutchComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) fillFields(newV, clutchNameField, clutchModelField, clutchProducerField, clutchWeightField, clutchPriceField, null, null);
        });
        gearboxComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                fillFields(newV, gearboxNameField, gearboxModelField, gearboxProducerField, gearboxWeightField, gearboxPriceField, gearsCountField, "6");
                if (newV.getSprzeglo() != null) clutchComboBox.getSelectionModel().select(newV.getSprzeglo());
            }
        });
    }

    private void fillFields(Komponent k, TextField n, TextField m, TextField p, TextField w, TextField c, TextField extra, String extraVal) {
        n.setText(k.getNazwa());
        m.setText(k.getModel());
        p.setText(k.getProducer());
        w.setText(String.valueOf(k.getWaga()));
        c.setText(String.valueOf(k.getCena()));
        if (extra != null) extra.setText(extraVal);
    }

    // --- ZMODYFIKOWANA METODA ZATWIERDZANIA ---
    @FXML
    private void onConfirmButton() {
        try {
            // 1. Sprawdzenie, czy pola tekstowe nie są puste (opcjonalne, ale warto)
            if (modelTextField.getText().isEmpty() || registrationTextField.getText().isEmpty()) {
                pokazBlad("Brak danych", "Pole Model i Rejestracja nie mogą być puste!");
                return;
            }


            int sprzegloWaga = Integer.parseInt(clutchWeightField.getText());
            int sprzegloCena = Integer.parseInt(clutchPriceField.getText());

            int skrzyniaWaga = Integer.parseInt(gearboxWeightField.getText());
            int skrzyniaCena = Integer.parseInt(gearboxPriceField.getText());
            int iloscBiegow = Integer.parseInt(gearsCountField.getText());

            int silnikWaga = Integer.parseInt(engineWeightField.getText());
            int silnikCena = Integer.parseInt(enginePriceField.getText());
            int maxObroty = Integer.parseInt(engineMaxRevField.getText());

            int predkosc = Integer.parseInt(speedTextField.getText());

            // Tworzenie obiektów (wykona się tylko jeśli parsowanie przeszło pomyślnie)
            Sprzeglo s = new Sprzeglo(clutchNameField.getText(), sprzegloWaga, sprzegloCena, clutchModelField.getText(), clutchProducerField.getText());
            Skrzynia sk = new Skrzynia(gearboxNameField.getText(), skrzyniaWaga, skrzyniaCena, gearboxModelField.getText(), gearboxProducerField.getText(), s, iloscBiegow);
            Silnik si = new Silnik(engineNameField.getText(), silnikWaga, silnikCena, engineModelField.getText(), engineProducerField.getText(), maxObroty);

            Samochód auto = new Samochód(registrationTextField.getText(), modelTextField.getText(), predkosc, si, sk);

            // Przekazanie do głównego okna
            if (parentController != null) {
                parentController.dodajSamochod(auto);
            }

            // Zamknięcie okna po sukcesie
            ((Stage) confirmButton.getScene().getWindow()).close();

        } catch (NumberFormatException e) {
            // Ten blok łapie błędy parsowania (Integer.parseInt)
            pokazBlad("Błędny format danych", "Pola liczbowe (Waga, Cena, Prędkość, Obroty) muszą zawierać tylko cyfry!\nSprawdź czy nie ma spacji lub liter.");
        } catch (Exception e) {
            // Ten blok łapie wszystkie inne dziwne błędy
            e.printStackTrace(); // Warto zostawić w konsoli dla programisty
            pokazBlad("Błąd tworzenia", "Wystąpił nieoczekiwany błąd przy tworzeniu obiektu: " + e.getMessage());
        }
    }

    // Metoda pomocnicza do wyświetlania okienka
    private void pokazBlad(String tytul, String tresc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(tytul);
        alert.setHeaderText(null);
        alert.setContentText(tresc);
        alert.showAndWait();
    }

    private void setupConverters() {
        StringConverter<Komponent> sc = new StringConverter<>() {
            @Override public String toString(Komponent k) { return k == null ? "" : k.getNazwa(); }
            @Override public Komponent fromString(String s) { return null; }
        };
        engineComboBox.setConverter((StringConverter)sc);
        gearboxComboBox.setConverter((StringConverter)sc);
        clutchComboBox.setConverter((StringConverter)sc);
    }

    @FXML private void onCancelButton() { ((Stage) confirmButton.getScene().getWindow()).close(); }
}