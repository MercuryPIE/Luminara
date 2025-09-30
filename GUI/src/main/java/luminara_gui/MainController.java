package luminara_gui;

import javafx.scene.control.Alert;
import luminara_core.properties.*;
import luminara_core.Core;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainController {

    @FXML
    private Button SelectFileButton, ConvertFileButton, SaveSettingsButton;

    private File ExtractFile;
    private ImageView SettingsIcon;

    @FXML
    private Label VersionLabel;

    @FXML
    public void initialize(){
        SaveSettingsButton.setDisable(true);
        VersionLabel.setText(AppProperties.get("APP_VERSION"));
    }


    @FXML
    private void HandleSelectFile(){
        FileChooser FC = new FileChooser();
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = (Stage) SelectFileButton.getScene().getWindow();

        File SelectedFile = FC.showOpenDialog(stage);

        if (SelectedFile != null){
            ConvertFileButton.setDisable(false);
            ExtractFile = SelectedFile;
        }
    }

    @FXML
    private void HandleSaveFile(){
        FileChooser FC = new FileChooser();

        String InitialFN = AppProperties.get("DEFAULT_FILE_NAME");


        FC.setInitialFileName(InitialFN);
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        Stage stage = (Stage) SelectFileButton.getScene().getWindow();

        File SaveLocation = FC.showSaveDialog(stage);

        if (SaveLocation != null){
            try{
                Core.ReadFile(ExtractFile, SaveLocation);

                ExtractFile = null;
                ConvertFileButton.setDisable(true);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void DisplayAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File To Small");
        alert.setHeaderText("The File is Too small");
        alert.setContentText("the file you have selected is too small, please select a file larger");
        alert.showAndWait();
    }
}