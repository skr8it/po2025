package po.samochodgui;

import auto.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException; // Dodany import do obsługi błędów plików

public class SamochodController implements Listener {
    @FXML private Label lblPredkoscDuza;
    @FXML private TextField txtModel, txtRejestracja, txtWagaAuto, txtPredkosc;
    @FXML private TextField txtSkrzyniaNazwa, txtSkrzyniaCena, txtSkrzyniaWaga, txtBieg;
    @FXML private TextField txtSilnikNazwa, txtSilnikCena, txtSilnikWaga, txtObroty;
    @FXML private TextField txtSprzegloNazwa, txtSprzegloCena, txtSprzegloWaga, txtSprzegloStan;

    @FXML private ComboBox<Samochód> comboSamochod;
    @FXML private AnchorPane mapa;
    @FXML private ImageView carImage;

    private ObservableList<Samochód> samochody = FXCollections.observableArrayList();
    private Samochód aktualnySamochod;

    @FXML
    public void initialize() {
        comboSamochod.setItems(samochody);

        // Ustawienie zachowania przy wyborze z listy
        comboSamochod.setOnAction(event -> {
            if (aktualnySamochod != null) {
                aktualnySamochod.removeListener(this);
            }
            aktualnySamochod = comboSamochod.getSelectionModel().getSelectedItem();
            if (aktualnySamochod != null) {
                aktualnySamochod.addListener(this);
                odswiezWidok();
            }
        });

        if (mapa != null) {
            mapa.setOnMouseClicked(event -> {
                if (aktualnySamochod != null) {
                    try {
                        aktualnySamochod.jedzDo(new Pozycja(event.getX(), event.getY()));
                        System.out.println("Jedziemy do: " + event.getX() + ", " + event.getY());
                    } catch (Exception e) {
                        pokazBlad("Błąd nawigacji", "Nie można ustalić celu: " + e.getMessage());
                    }
                }
            });
        }

        // --- POPRAWIONE AUTOMATYCZNE DODANIE AUTA ---
        try {
            // 1. Tworzymy podzespoły
            Sprzeglo sprzegloStart = new Sprzeglo("Standardowe", 15, 600, "Clutch-1", "Valeo");
            Skrzynia skrzyniaStart = new Skrzynia("Manual 5b", 50, 2000, "G-Box", "ZF", sprzegloStart, 5);
            Silnik silnikStart = new Silnik("1.9 TDI", 150, 4000, "TDI-100", "VW", 5000);

            // 2. Tworzymy auto
            Samochód autoStartowe = new Samochód("KR START", "Golf IV Test", 190, silnikStart, skrzyniaStart);

            // 3. Dodajemy do listy
            samochody.add(autoStartowe);

            // 4. WYMUSZAMY WYBÓR RĘCZNIE (to naprawia problem pustych pól)
            comboSamochod.getSelectionModel().select(autoStartowe);
            aktualnySamochod = autoStartowe; // Ustawiamy zmienną bezpośrednio
            aktualnySamochod.addListener(this); // Dodajemy nasłuchiwanie
            odswiezWidok(); // Ręcznie wywołujemy odświeżenie pól tekstowych

        } catch (Exception e) {
            System.err.println("Nie udało się dodać auta startowego: " + e.getMessage());
            e.printStackTrace(); // Wypisze dokładny błąd w konsoli jeśli coś pójdzie nie tak
        }
    }

    private void wyczyscPola() {
        txtModel.clear(); txtRejestracja.clear(); txtWagaAuto.clear(); txtPredkosc.clear();
        txtSilnikNazwa.clear(); txtSilnikCena.clear(); txtSilnikWaga.clear(); txtObroty.clear();
        txtSkrzyniaNazwa.clear(); txtSkrzyniaCena.clear(); txtSkrzyniaWaga.clear(); txtBieg.clear();
        txtSprzegloNazwa.clear(); txtSprzegloCena.clear(); txtSprzegloWaga.clear(); txtSprzegloStan.clear();
        lblPredkoscDuza.setText("0.0 km/h");
        if (carImage != null) carImage.setVisible(false);
    }

    public void odswiezWidok() {
        if (aktualnySamochod == null) return;

        Platform.runLater(() -> {
            if (carImage != null) carImage.setVisible(true);

            // Samochód podstawowe
            txtModel.setText(aktualnySamochod.model);
            txtRejestracja.setText(aktualnySamochod.nrRejest);
            txtWagaAuto.setText(String.valueOf(aktualnySamochod.getWaga()));
            txtPredkosc.setText(String.format("%.2f", aktualnySamochod.getAktPredkosc()));
            lblPredkoscDuza.setText(String.format("%.0f km/h", aktualnySamochod.getAktPredkosc()));

            // Silnik
            txtSilnikNazwa.setText(aktualnySamochod.silnik.getNazwa());
            txtSilnikWaga.setText(String.valueOf(aktualnySamochod.silnik.getWaga()));
            txtSilnikCena.setText(String.valueOf(aktualnySamochod.silnik.getCena()));
            txtObroty.setText(String.valueOf(aktualnySamochod.silnik.getObroty()));

            // Skrzynia
            txtSkrzyniaNazwa.setText(aktualnySamochod.skrzynia.getNazwa());
            txtSkrzyniaWaga.setText(String.valueOf(aktualnySamochod.skrzynia.getWaga()));
            txtSkrzyniaCena.setText(String.valueOf(aktualnySamochod.skrzynia.getCena()));
            txtBieg.setText(String.valueOf(aktualnySamochod.skrzynia.getAktualny_bieg()));

            // Sprzęgło
            Sprzeglo sprzeglo = aktualnySamochod.skrzynia.getSprzeglo();
            txtSprzegloNazwa.setText(sprzeglo.getNazwa());
            txtSprzegloWaga.setText(String.valueOf(sprzeglo.getWaga()));
            txtSprzegloCena.setText(String.valueOf(sprzeglo.getCena()));
            txtSprzegloStan.setText(sprzeglo.isStanSprzegla() ? "Wciśnięte" : "Zwolnione");

            // Ruch ikony
            if (carImage != null) {
                double polSzerokosci = carImage.getFitWidth() / 2;
                double polWysokosci = carImage.getFitHeight() / 2;
                carImage.setTranslateX(aktualnySamochod.getPozycja().getX() - 2.6*polSzerokosci);
                carImage.setTranslateY(aktualnySamochod.getPozycja().getY() - 2.1*polWysokosci);
            }
        });
    }

    @Override public void update() { odswiezWidok(); }

    public void dodajSamochod(Samochód nowy) {
        samochody.add(nowy);
        comboSamochod.getSelectionModel().select(nowy);
    }

    // --- ZMODYFIKOWANE METODY STEROWANIA (Try-Catch dla logiki) ---

    @FXML private void onWlacz() {
        if (aktualnySamochod != null) {
            try {
                // Jeśli w klasie Samochód jest 'if(wlaczony) throw new Exception("Już działa")',
                // to tutaj to złapiemy.
                aktualnySamochod.wlacz();
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd silnika", "Nie można włączyć auta: " + e.getMessage());
            }
        }
    }

    @FXML private void onWylacz() {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.wylacz();
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd silnika", "Nie można wyłączyć auta: " + e.getMessage());
            }
        }
    }

    @FXML private void onZwiekszBieg() {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.skrzynia.zwiekszBieg();
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd skrzyni biegów", "Nie można zmienić biegu: " + e.getMessage());
            }
        }
    }

    @FXML private void onZmniejszBieg() {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.skrzynia.zmniejszBieg();
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd skrzyni biegów", "Nie można zredukować biegu: " + e.getMessage());
            }
        }
    }

    @FXML private void onDodajGazu() {
        if (aktualnySamochod != null) {
            try {
                // To wyłapie sytuację, gdy np. silnik jest wyłączony
                // (O ile metoda zwiekszObroty rzuca wtedy wyjątek)
                aktualnySamochod.silnik.zwiekszObroty(500);
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd operacji", "Nie można dodać gazu: " + e.getMessage());
            }
        }
    }

    @FXML private void onUjmijGazu() {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.silnik.zmniejszObroty(500);
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd operacji", "Nie można ująć gazu: " + e.getMessage());
            }
        }
    }

    @FXML private void onNacisnijSprzeglo() {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.skrzynia.getSprzeglo().wcisnij();
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd sprzęgła", "Nie można wcisnąć sprzęgła: " + e.getMessage());
            }
        }
    }

    @FXML private void onZwolnijSprzeglo() {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.skrzynia.getSprzeglo().zwolnij();
                odswiezWidok();
            } catch (Exception e) {
                pokazBlad("Błąd sprzęgła", "Nie można zwolnić sprzęgła: " + e.getMessage());
            }
        }
    }

    // --- OBSŁUGA OKNA DODAWANIA ---

    @FXML
    private void onDodajNowy(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
            Parent root = loader.load();

            DodajSamochodController child = loader.getController();
            child.setParentController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Kreator Samochodu");
            stage.show();

        } catch (IOException e) {
            // Obsługa błędu ładowania pliku FXML
            e.printStackTrace();
            pokazBlad("Błąd pliku", "Nie udało się otworzyć okna dodawania: " + e.getMessage());
        } catch (Exception e) {
            // Inne nieprzewidziane błędy
            e.printStackTrace();
            pokazBlad("Błąd krytyczny", "Wystąpił nieoczekiwany błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onUsun(ActionEvent event) {
        if (aktualnySamochod != null) {
            try {
                aktualnySamochod.removeListener(this);
                samochody.remove(aktualnySamochod);

                if (!samochody.isEmpty()) {
                    comboSamochod.getSelectionModel().selectFirst();
                } else {
                    aktualnySamochod = null;
                    comboSamochod.getSelectionModel().clearSelection();
                    wyczyscPola();
                }
            } catch (Exception e) {
                pokazBlad("Błąd usuwania", "Nie udało się usunąć pojazdu: " + e.getMessage());
            }
        } else {
            pokazBlad("Błąd", "Nie wybrano samochodu do usunięcia!");
        }
    }

    private void pokazBlad(String tytul, String tresc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(tytul);
        alert.setHeaderText(null);
        alert.setContentText(tresc);
        alert.showAndWait();
    }

    public ComboBox<Samochód> getComboSamochod() { return comboSamochod; }
}