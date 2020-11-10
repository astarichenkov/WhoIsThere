package ru.whoisthere.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import static ru.whoisthere.utils.Logging.addInfoLog;

public class Departments {
    private List<List<String>> departs = new ArrayList<>();

    public Departments() {
        String role = "ADMIN";
        if (role.equals("ADMIN")) {
            File file = new File("departs.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file, StandardCharsets.UTF_8))) {

                for (int i = 0; i < 16; i++) {
                    String s = reader.readLine();
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
