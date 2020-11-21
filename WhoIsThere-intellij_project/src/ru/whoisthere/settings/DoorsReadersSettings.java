package ru.whoisthere.settings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
//            File file = new File("doorsReaders.txt");
//            try {
//                file.setExecutable(false);
//                file.setReadable(true);
//                file.setWritable(true);
//            } catch (SecurityException e) {
//                addInfoLog(e.getMessage() + "Exception in set file attributes");
//            }
            Path path = Paths.get("doorsReaders.txt");
            try {
                List<String> params = Files.readAllLines(path);
//);
                inputHall = Integer.parseInt(params.get(0));
                outputHall = Integer.parseInt(params.get(1));
                inputMag = Integer.parseInt(params.get(2));
                exitMag = Integer.parseInt(params.get(3));
                addInfoLog("Settings file doors.txt read.");
            } catch (IOException e) {
                addInfoLog(e.getMessage() + "File reading error doors.txt");
            }
        }
    }
}
