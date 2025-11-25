module po.samgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens po.samgui to javafx.fxml;
    exports po.samgui;
}