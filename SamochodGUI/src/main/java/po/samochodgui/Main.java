package po.samochodgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Ładowanie głównego widoku Twojej aplikacji
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SamochodGUI.fxml"));
        Parent root = loader.load();

        // Ustawienie tytułu i sceny
        primaryStage.setTitle("Symulator Samochodu - JavaFX");
        primaryStage.setScene(new Scene(root));

        // Wyświetlenie okna
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}