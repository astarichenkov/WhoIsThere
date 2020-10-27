package ru.whoisthere.settings;

import ru.whoisthere.utils.Loging;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class DoorsReadersSettings {
    private static Loging logs = new Loging();
    private int inputHall;
    private int outputHall;
    private int inputMag;
    private int exitMag;

    public DoorsReadersSettings() {
        readFile();
    }

    public int getInputHall() {
        return inputHall;
    }

    public int getOutputHall() {
        return outputHall;
    }

    public int getInputMag() {
        return inputMag;
    }

    public int getExitMag() {
        return exitMag;
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
            File file = new File(userDir, "doorsReaders.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    file), StandardCharsets.UTF_8))) {
                this.inputHall = Integer.parseInt(reader.readLine());
                this.outputHall = Integer.parseInt(reader.readLine());
                this.inputMag = Integer.parseInt(reader.readLine());
                this.exitMag = Integer.parseInt(reader.readLine());
                logs.addInfoLog("Settings file doors.txt read.");
            } catch (IOException e) {
                logs.addInfoLog(e.getMessage() + "File reading error doors.txt");
            }
        }
    }
}
