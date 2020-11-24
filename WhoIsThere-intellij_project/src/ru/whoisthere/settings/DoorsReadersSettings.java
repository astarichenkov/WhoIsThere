package ru.whoisthere.settings;

import java.io.*;
import java.util.Properties;

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
        File file = new File("doorsReaders.properties");
        try {
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(false);
        } catch (SecurityException e) {
            addInfoLog(e.getMessage() + "Exception in set file attributes");
        }
        Properties props = new Properties();
        try {
            props.load(new FileReader(file));
        } catch (IOException e) {
            addInfoLog(e.getMessage() + "error loading doorsReaders.properties");
        }

        try {
            inputHall = Integer.parseInt(props.getProperty("inputHall"));
            outputHall = Integer.parseInt(props.getProperty("outputHall"));
            inputMag = Integer.parseInt(props.getProperty("inputMag"));
            exitMag = Integer.parseInt(props.getProperty("exitMag"));
            addInfoLog("Settings file doorsReaders.properties read.");
        } catch (NumberFormatException e) {
            addInfoLog(e.getLocalizedMessage() + " error read doorsReaders.properties");
        }
    }
}
