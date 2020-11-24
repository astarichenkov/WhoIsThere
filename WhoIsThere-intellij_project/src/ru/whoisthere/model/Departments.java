package ru.whoisthere.model;

import java.io.*;
import java.util.*;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class Departments {
    private List<String> departs = new ArrayList<>();

    public Departments() {
        File file = new File("departs.properties");
        Properties props = new Properties();
        try {
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(false);
        } catch (SecurityException e) {
            addInfoLog(e.getMessage() + "Exception in set file attributes");
        }
        try {
            props.load(new FileReader(file));
        } catch (IOException e) {
            addInfoLog(e.getMessage() + "error loading connection.properties");
        }
        for (int i = 0; i < 16; i++) {
            String s = "depart" + i;
            departs.add(props.getProperty(s));
        }

    }

    public String getDepartmentName(int otdelIndex) {
        if (otdelIndex < departs.size()) {
            return departs.get(otdelIndex);
        } else {
            return null;
        }
    }

    public List<String> getDepartments() {
        return departs;
    }
}