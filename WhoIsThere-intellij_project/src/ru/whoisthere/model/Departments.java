package ru.whoisthere.model;

import ru.whoisthere.utils.Loging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Departments {
    private static Loging logs = new Loging();
    private List<List<String>> departs = new ArrayList<List<String>>();

    public Departments() {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                "C:\\WhoIsThere\\departs.txt"), StandardCharsets.UTF_8))) {
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            departs.add(Arrays.asList(reader.readLine().split(", ")));
            logs.addInfoLog("Settings file departs.txt read.");
        } catch (IOException e) {
            logs.addInfoLog("File reading error departs.txt");
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