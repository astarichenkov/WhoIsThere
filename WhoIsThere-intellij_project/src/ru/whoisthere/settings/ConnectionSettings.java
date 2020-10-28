package ru.whoisthere.settings;

import ru.whoisthere.utils.Loging;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.text.StringEscapeUtils.*;

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
        String host = "";
        String role = "";
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            logs.addInfoLog(e.getMessage());
        }
        if (host.contains("leroymerlin")) {
            role = "ADMIN";
        }

        if (role.equals("ADMIN")) {
            String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            try {
                userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            } catch (SecurityException e) {
                logs.addInfoLog(e.getMessage());
            }
            File file = new File("connection.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

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
