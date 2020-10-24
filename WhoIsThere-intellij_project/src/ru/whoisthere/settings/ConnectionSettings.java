package ru.whoisthere.settings;

import ru.whoisthere.utils.Loging;
import ru.whoisthere.utils.SanitizePath;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

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
//        String filename = SanitizePath.sanitizePathTraversal("connection.txt");
//        File file = new File(filename);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                file), StandardCharsets.UTF_8))) {
            this.user = escapeHtml4(reader.readLine());
            this.asswd = escapeHtml4(reader.readLine());
            this.ip = escapeHtml4(reader.readLine());
            this.pathToDB = escapeHtml4(reader.readLine());

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
