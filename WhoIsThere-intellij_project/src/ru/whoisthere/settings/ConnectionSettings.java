package ru.whoisthere.settings;

import ru.whoisthere.utils.Loging;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConnectionSettings {
    private Loging logs = new Loging();
    private String user;
    private String asswd;
    private String ip;
    private String pathToDB;

    public ConnectionSettings() {
        readFile();
    }

    private void readFile() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        File file = new File(userDir, "connection.txt");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                file), StandardCharsets.UTF_8))) {
            this.user = reader.readLine();
            this.asswd = reader.readLine();
            this.ip = reader.readLine();
            this.pathToDB = reader.readLine();

            logs.addInfoLog("Settings file connection.txt read.");
        } catch (IOException e) {
            logs.addInfoLog(e.getMessage() + " File reading error connection.txt");
        }
    }

    public String getLogin() {
        return user;
    }

    public String getAsswd() {
        return asswd;
    }

    public String getIp() {
        return ip;
    }

    public String getPathToDB() {
        return pathToDB;
    }
}
