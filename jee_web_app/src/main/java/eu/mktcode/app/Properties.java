package eu.mktcode.app;

import java.util.Locale;
import java.util.ResourceBundle;

public class Properties {
    private static final ResourceBundle PROPERTIES_FILE =
            ResourceBundle.getBundle(PropertiesNames.PROPERTIES_FILE_NAME, Locale.getDefault());

    public static String getDataSource() {
        return PROPERTIES_FILE.getString(PropertiesNames.DATABASE_NAME);
    }

    public static int getPoolSize() {
        //TODO logging
        return Integer.valueOf(PROPERTIES_FILE.getString(PropertiesNames.DATABASE_POOL_SIZE));
    }

    public static int getConnectionWaitingTime() {
        //TODO logging
        return Integer.valueOf(PROPERTIES_FILE.getString(PropertiesNames.WAIT_TIME_IF_ALL_CONNECTIONS_BUSY));
    }

    public static String getDataBaseURL() {
        return PROPERTIES_FILE.getString(PropertiesNames.DATABASE_URL);
    }
    public static String getDataBaseUser() {
        return PROPERTIES_FILE.getString(PropertiesNames.DATABASE_USER);
    }
    public static String getDataBasePassword() {
        return PROPERTIES_FILE.getString(PropertiesNames.DATABASE_PASSWORD);
    }
}
