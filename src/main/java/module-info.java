module com.example.luminaragui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires fr.brouillard.oss.cssfx;
    requires java.prefs;


    opens LuminaraApp.LuminaraGUI to javafx.fxml;
    exports LuminaraApp.LuminaraGUI;
}