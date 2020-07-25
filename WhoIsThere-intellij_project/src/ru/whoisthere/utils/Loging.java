package ru.whoisthere.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Loging {
    private static Logger logger = Logger.getLogger("Logs");
    private static FileHandler fh;

    public Loging() {
        try {
            fh = new FileHandler("C:\\WhoIsThere\\logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            //formatter.format();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addInfoLog(String info) {
        if (info != null) {
            logger.log(Level.INFO, info);
        }
    }

    public void addWarningLog(String warning) {
        if (warning != null) {
            logger.log(Level.WARNING, warning);
        }
    }

}