module win.bitforge {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.prefs;


    opens Luminara.GUI to javafx.fxml;
    exports Luminara.GUI;
}