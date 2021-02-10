package ru.whoisthere.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    private static Logger logger = Logger.getLogger("Logs");
    private static FileHandler fh;

    public Logging() {
        try {
            fh = new FileHandler("logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.getMessage();
        }
    }

    public static void addInfoLog(String info) {
        if (info != null) {
            logger.log(Level.INFO, info);
        }
    }

    public static void addWarningLog(String warning) {
        if (warning != null) {
            logger.log(Level.WARNING, warning);
        }
    }

}