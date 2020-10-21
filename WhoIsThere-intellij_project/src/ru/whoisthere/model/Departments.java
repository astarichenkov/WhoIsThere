package ru.whoisthere.model;

import ru.whoisthere.utils.Loging;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Departments {
    private static Loging logs = new Loging();
    private List<List<String>> departs = new ArrayList<List<String>>();

    public Departments() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        File file = new File(userDir, "departs.txt");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                file), StandardCharsets.UTF_8))) {

            for (int i = 0; i < 16; i++) {
                departs.add(readAndFilterString(reader.readLine()));
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

    private List<String> readAndFilterString(String s) {
        String s2 = s.trim();
        Pattern pattern = Pattern.compile(".*, .*");
        Matcher matcher = pattern.matcher(s);
        boolean matches = matcher.matches();
        List<String> list = new ArrayList<>();
        if (matches) {
            list = Arrays.asList(s2.split(", "));
        } else {
            logs.addInfoLog("Error in pattern of file");
            list.add("New");
            list.add("wrong parameter");
        }
        return list;
    }
}
