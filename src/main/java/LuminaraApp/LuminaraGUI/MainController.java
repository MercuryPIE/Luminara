package LuminaraApp.LuminaraGUI;

import LuminaraApp.LuminaraCore.Core;
import LuminaraApp.LuminaraCore.ThemeManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    private Button SelectFileButton, ConvertFileButton, SwitchThemeButton, SaveSettingsButton;

    private File ExtractFile;
    private ImageView ThemeIcon, SettingsIcon;

    @FXML
    public void initialize(){
        ThemeIcon = new ImageView();
        UpdateButtonIcon();
        SaveSettingsButton.setDisable(true);
    }

    private void UpdateButtonIcon(){

        String ThemeIconPath = ThemeManager.LoadTheme() ? "Icons/Light-Mode-Icon.png" : "Icons/Dark-Mode-Icon.png";

        ThemeIcon.setImage(new Image(Objects.requireNonNull(LuminaraApp.class.getResourceAsStream(ThemeIconPath))));

        ThemeIcon.setFitHeight(24);
        ThemeIcon.setFitWidth(24);

        ThemeIcon.setPreserveRatio(true);

        SwitchThemeButton.setGraphic(ThemeIcon);
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
    private void HandleThemeButton(){
        Scene scene = SwitchThemeButton.getScene();
        ThemeManager.ToggleTheme(scene);
        UpdateButtonIcon();
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