package LuminaraApp.LuminaraGUI;

import LuminaraApp.LuminaraCore.ThemeManager;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LuminaraApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader root = new FXMLLoader(LuminaraApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root.load(), 700, 400); // width: 700, height: 400.

        stage.getIcons().add(new Image(Objects.requireNonNull(LuminaraApp.class.getResourceAsStream("Icons/Luminara-Main-Icon.png"))));

        ThemeManager.ApplyTheme(scene);

        CSSFX.start();

        stage.setMinWidth(460);
        stage.setMinHeight(340);

        stage.setTitle("Luminara Beta");
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}