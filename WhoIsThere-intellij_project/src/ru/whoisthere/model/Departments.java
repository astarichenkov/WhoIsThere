package ru.whoisthere.model;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import ru.whoisthere.utils.Loging;
import ru.whoisthere.utils.SanitizePath;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Departments {
    private static Loging logs = new Loging();
    private List<List<String>> departs = new ArrayList<>();

    public Departments() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        File file = new File(userDir, "departs.txt");
//        String filename = SanitizePath.sanitizePathTraversal("departs.txt");
//        File file = new File(filename);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                file), StandardCharsets.UTF_8))) {

            for (int i = 0; i < 16; i++) {
                String s = reader.readLine();
                String escaped = escapeHtml4(s);
                departs.add(Arrays.asList(escaped.split(", ")));
            }
        } catch (IOException e) {
            logs.addInfoLog(e.getMessage() + " File reading error departs.txt");
        }
    }

    public int getOtdelsCount() {
        return departs.size();
    }

    public String getDepartmentTitle(int otdelIndex) {
        if (otdelIndex < departs.size()) {
            return departs.get(otdelIndex).get(1);
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
