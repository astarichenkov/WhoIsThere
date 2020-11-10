package ru.whoisthere.settings;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class DoorsReadersSettings {
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
        String role = "ADMIN";
        if (role.equals("ADMIN")) {
            File file = new File("doorsReaders.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file, StandardCharsets.UTF_8))) {
                this.inputHall = Integer.parseInt(reader.readLine());
                this.outputHall = Integer.parseInt(reader.readLine());
                this.inputMag = Integer.parseInt(reader.readLine());
                this.exitMag = Integer.parseInt(reader.readLine());
                addInfoLog("Settings file doors.txt read.");
            } catch (IOException e) {
                addInfoLog(e.getMessage() + "File reading error doors.txt");
            }
        }
    }
}
