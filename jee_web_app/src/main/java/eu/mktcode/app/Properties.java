package eu.mktcode.app;

import java.util.Locale;
import java.util.ResourceBundle;

public class Properties {
    private static final ResourceBundle PROPERTIES_FILE =
            ResourceBundle.getBundle(PropertiesNames.PROPERTIES_FILE_NAME, Locale.getDefault());

    public static String getDataSource() {
        return PROPERTIES_FILE.getString(PropertiesNames.DATABASE);
    }
}
