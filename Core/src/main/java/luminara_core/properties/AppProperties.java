package luminara_core.properties;

import luminara_core.error.PropertyFileNotFound;
import luminara_core.error.PropertyKeyNotFound;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private static final Properties props = new Properties();

    static {
        try (InputStream inputStream = AppProperties.class.getResourceAsStream("/app.properties")){

            if (inputStream == null){
                throw new PropertyFileNotFound("Failed to find app.properties file.");
            }

            props.load(inputStream);

        } catch (IOException _){
            throw new PropertyFileNotFound("Failed to find app.properties file.");
        }
    }

    /// @param Key is used to get a value from the app.properties file.
    public static String get(String Key){

        if (!props.containsKey(Key)){
            throw new PropertyKeyNotFound(Key + ": was not found in app.properties file");
        }

        return props.getProperty(Key);
    }
}

