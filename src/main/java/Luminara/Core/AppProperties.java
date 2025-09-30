package Luminara.Core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private static final Properties props = new Properties();

    static{
        try(InputStream in = AppProperties.class.getResourceAsStream("/app.properties")) {
            if (in != null){
                props.load(in);
            } else {
                System.err.println("Properties file missing.");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String get(PropertyManager PM){
        return props.getProperty(PM.Key(), PM.Default());
    }
}

