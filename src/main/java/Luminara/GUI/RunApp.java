package Luminara.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RunApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader root = new FXMLLoader(RunApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root.load(), 700, 400); // width: 700, height: 400.

        scene.getStylesheets().add(Objects.requireNonNull(RunApp.class.getResource("Themes/Dark-Theme.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(RunApp.class.getResourceAsStream("Icons/Luminara-Main-Icon.png"))));

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