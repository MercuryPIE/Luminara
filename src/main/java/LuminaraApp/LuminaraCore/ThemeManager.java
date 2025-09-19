package LuminaraApp.LuminaraCore;

import LuminaraApp.LuminaraGUI.LuminaraApp;
import javafx.scene.Scene;

import java.util.Objects;
import java.util.prefs.Preferences;

public class ThemeManager {
    private static final String PreferenceKey = "IsDarkMode";

    private static Preferences GetPreferences(){
        return Preferences.userNodeForPackage(ThemeManager.class);
    }

    public static void SaveTheme(boolean IsDarkMode){
        GetPreferences().putBoolean(PreferenceKey, IsDarkMode);
    }

    public static boolean LoadTheme(){
        return GetPreferences().getBoolean(PreferenceKey, false);
    }

    public static void ApplyTheme(Scene scene){
        scene.getStylesheets().clear();

        String ThemeName = LoadTheme() ? "Themes/Dark-Theme.css" : "Themes/Light-Theme.css";
        scene.getStylesheets().add(Objects.requireNonNull(LuminaraApp.class.getResource(ThemeName)).toExternalForm());
    }

    public static void ToggleTheme(Scene scene){
        SaveTheme(!LoadTheme()); // Flip Flops the theme to toggle it.
        ApplyTheme(scene);

    }
}
