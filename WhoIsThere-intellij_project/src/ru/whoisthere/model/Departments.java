package ru.whoisthere.model;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import static ru.whoisthere.utils.Logging.addInfoLog;

public class Departments {
    private List<List<String>> departs = new ArrayList<>();

    public Departments() {
        String role = "ADMIN";
        if (role.equals("ADMIN")) {
            File file = new File("departs.txt");
            try {
                file.setExecutable(false);
                file.setReadable(true);
                file.setWritable(true);
            } catch (SecurityException e) {
                addInfoLog(e.getMessage() + "Exception in set file attributes");
            }

            try {
                List<String> lines = Files.readAllLines(file.toPath());
                for (String s : lines) {
                    String escaped = escapeHtml4(s);
                    departs.add(Arrays.asList(escaped.split(", ")));
                }
            } catch (IOException e) {
                addInfoLog(e.getMessage() + " File reading error departs.txt");
            }
        }
    }

    public String getDepartmentTitle(int index) {
        if (index < departs.size()) {
            return departs.get(index).get(1);
        } else {
            return null;
        }
    }

    public String getDepartmentName(int otdelIndex) {
        if (otdelIndex < departs.size()) {
            return departs.get(otdelIndex).get(0);
        } else {
            return null;
        }
    }

    public List<List<String>> getDepartments() {
        return departs;
    }
}
