package ru.whoisthere.settings;

import ru.whoisthere.utils.Loging;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

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
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        File file = new File(userDir, "doorsReaders.txt");

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
            logs.addInfoLog("File reading error doors.txt");
        }
    }
}
