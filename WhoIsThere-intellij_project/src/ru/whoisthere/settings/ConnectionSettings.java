package ru.whoisthere.settings;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class ConnectionSettings {
    private static String user;
    private static String asswd;
    private static String ip;
    private static String pathToDB;

    public ConnectionSettings() {
        readFile();
    }

    public static void readFile() {

        String role = "ADMIN";

        if (role.equals("ADMIN")) {

            File file = new File("connection.txt");
            try {
                file.setExecutable(false);
                file.setReadable(true);
                file.setWritable(true);
            } catch (SecurityException e) {
                addInfoLog(e.getMessage() + "Exception in set file attributes");
            }

            try  {
                List<String> params = Files.readAllLines(file.toPath());
                user = params.get(0);
                asswd = params.get(1);
                ip = params.get(2);
                pathToDB = params.get(3);

                addInfoLog("Settings file connection.txt read.");
            } catch (IOException e) {
                addInfoLog(e.getMessage() + " File reading error connection.txt");
            }
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
