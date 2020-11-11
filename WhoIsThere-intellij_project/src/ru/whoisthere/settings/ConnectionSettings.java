package ru.whoisthere.settings;

import java.io.*;
import java.util.Properties;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class ConnectionSettings {
    private static String user;
    private static String asswd;
    private static String ip;
    private static String pathToDB;
    private static Properties props = new Properties();

    public ConnectionSettings() {
        readFile();
    }

    public static void readFile() {
        File file = new File("connection.properties");
        try {
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);
        } catch (SecurityException e) {
            addInfoLog(e.getMessage() + "Exception in set file attributes");
        }

        try {
            props.load(new FileReader(file));
        } catch (IOException e) {
            addInfoLog(e.getMessage() + "error loading connection.properties");
        }

        user = props.getProperty("user");
        asswd = props.getProperty("asswd");
        ip = props.getProperty("ip");
        pathToDB = props.getProperty("pathToDB");

        addInfoLog("Settings file connection.properties read.");
    }

    public static void writeToFile(Properties properties) {
        File file = new File("connection.properties");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            properties.store(fos, "comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLogin() {
        return user;
    }

    public static String getAsswd() {
        return asswd;
    }

    public static String getIp() {
        return ip;
    }

    public static String getPathToDB() {
        return pathToDB;
    }
}
