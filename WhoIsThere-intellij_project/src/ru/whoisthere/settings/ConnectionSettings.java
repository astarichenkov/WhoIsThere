package ru.whoisthere.settings;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.text.StringEscapeUtils.*;
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
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file, StandardCharsets.UTF_8))) {
                user = escapeHtml4(reader.readLine());
                asswd = escapeHtml4(reader.readLine());
                ip = escapeHtml4(reader.readLine());
                pathToDB = escapeHtml4(reader.readLine());

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
