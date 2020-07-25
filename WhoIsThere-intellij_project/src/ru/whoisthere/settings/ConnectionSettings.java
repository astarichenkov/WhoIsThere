package ru.whoisthere.settings;

import ru.whoisthere.utils.Loging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConnectionSettings {
    private Loging logs = new Loging();
    private String user;
    private String password;
    private String ip;
    private String pathToDB;

    public ConnectionSettings() {
        readFile();
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                "C:\\WhoIsThere\\connection.txt"), StandardCharsets.UTF_8))) {
            this.user = reader.readLine();
            this.password = reader.readLine();
            this.ip = reader.readLine();
            this.pathToDB = reader.readLine();

            logs.addInfoLog("Settings file connection.txt read.");
        } catch (IOException e) {
            logs.addInfoLog("File reading error connection.txt");
        }
    }

    public String getLogin() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getIp() {
        return ip;
    }

    public String getPathToDB() {
        return pathToDB;
    }
}
