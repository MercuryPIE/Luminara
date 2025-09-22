package Luminara.GUI;

import Luminara.Core.Core;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    public void initialize(){
        SaveSettingsButton.setDisable(true);
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
        FC.setInitialFileName("output");
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        Stage stage = (Stage) SelectFileButton.getScene().getWindow();
        File SaveLocation = FC.showSaveDialog(stage);

        if (SaveLocation != null){
            try{
                int ExitCode = Core.ReadFile(ExtractFile, SaveLocation);
                System.out.println(ExitCode);

                ExtractFile = null;
                ConvertFileButton.setDisable(true);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}