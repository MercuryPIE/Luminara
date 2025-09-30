module luminara.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires luminara.core;

    opens luminara_gui to javafx.fxml;
    exports luminara_gui;
}