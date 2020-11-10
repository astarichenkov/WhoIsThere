package ru.whoisthere.settings;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class DoorsReadersSettings {
    private static int inputHall;
    private static int outputHall;
    private static int inputMag;
    private static int exitMag;

    public DoorsReadersSettings() {
        readFile();
    }

    public static int getInputHall() {
        return inputHall;
    }

    public static int getOutputHall() {
        return outputHall;
    }

    public static int getInputMag() {
        return inputMag;
    }

    public static int getExitMag() {
        return exitMag;
    }

    public static void readFile() {
        String role = "ADMIN";
        if (role.equals("ADMIN")) {
            File file = new File("doorsReaders.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file, StandardCharsets.UTF_8))) {
                inputHall = Integer.parseInt(reader.readLine());
                outputHall = Integer.parseInt(reader.readLine());
                inputMag = Integer.parseInt(reader.readLine());
                exitMag = Integer.parseInt(reader.readLine());
                addInfoLog("Settings file doors.txt read.");
            } catch (IOException e) {
                addInfoLog(e.getMessage() + "File reading error doors.txt");
            }
        }
    }
}
