package ru.whoisthere.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;
import ru.whoisthere.settings.DoorsReadersSettings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;

public class SqlUtils {
    private static Loging logs = new Loging();
    private Departments departs = new Departments();
    private Connection con;
    private DoorsReadersSettings doors = new DoorsReadersSettings();

    public boolean openConnection(
            String serverAddress, String login, String password, String pathToDB) {

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        props.setProperty("encoding", "UTF8");
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String url = "jdbc:sqlserver://" + serverAddress;
            this.con = DriverManager.getConnection(
                    "jdbc:firebirdsql://" + serverAddress
                            + "/" + pathToDB, props);
            logs.addInfoLog("Connection to the server " + serverAddress + " was successful.");
            return true;
        } catch (SQLException e) {
            logs.addWarningLog(e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            logs.addWarningLog(e.getMessage());
            return false;
        }
    }

    public boolean closeConnection() {
        try {
            this.con.close();
            logs.addInfoLog("Disconnecting from the server.");
            return true;
        } catch (SQLException e) {
            logs.addWarningLog(e.getMessage());
            return false;
        }
    }

    public List<Person> execQuery() {
        int inputDoor = doors.getInputHall();
        int outputDoor = doors.getOutputHall();
        int exitDoor = doors.getExitMag();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String curDate = dateFormat.format(new Date());

        List<Person> persons = new ArrayList<Person>();
        String queryStr = "SELECT events.lname1, events.lname2, events.ldepartment, EVENTS.LPOST, EVENTS.POBJECT"
                + " from events where (d = (SELECT MAX(D) FROM EVENTS)"
                + " AND (POBJECT = " + inputDoor + " OR POBJECT = " + outputDoor
                + " OR POBJECT = " + exitDoor + ")) ORDER BY LNAME1, T ASC";

        ArrayList otdels = new ArrayList();
        for (int i = 0; i < 16; i++) {
            otdels.add(departs.getDepartmentName(i));
        }

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryStr);

            while (rs.next()) {
                Person person = new Person(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), new byte[10]);
                if (person.getName() == null || person.getSurname() == null
                        || person.getDepartment() == null) {
                    continue;
                }
                int pObject = rs.getInt(5);

                if (pObject == inputDoor) {
                    if (!persons.contains(person) && otdels.contains(person.getDepartment())) {
                        persons.add(person);
                    }
                    continue;
                }
                if (pObject == outputDoor || pObject == exitDoor) {
                    if (persons.contains(person)) {
                        persons.remove(person);
                    }
                }

            }
            persons = sotrByDepartment(persons);
            for (int i = 0; i < persons.size(); i++) {
                queryStr = "SELECT DISTINCT events.lname1, events.lname2, events.ldepartment,"
                        + " CARDS.PHOTO, EVENTS.POBJECT from events JOIN CARDS ON"
                        + " (EVENTS.LNAME1 = CARDS.NAME1 AND EVENTS.LNAME2 = CARDS.NAME2) where"
                        + " (d = (SELECT MAX(D) FROM EVENTS) AND "
                        + " (POBJECT = " + inputDoor + " OR POBJECT = " + exitDoor + ")"
                        + " AND EVENTS.LNAME1 = '" + persons.get(i).getName()
                        + "' AND EVENTS.LNAME2 = '" + persons.get(i).getSurname()
                        + "' AND CARDS.PHOTO IS NOT NULL)";
                rs = stmt.executeQuery(queryStr);
                if (rs.next()) {
                    persons.get(i).setPhoto(rs.getBytes(4));
                }
            }
            rs.close();
            stmt.close();
            logs.addInfoLog("Employee data is received. " + persons.size() + " records.");
        } catch (
                SQLException e) {
            logs.addWarningLog(e.getMessage());
        } finally {
            closeConnection();
        }

        return persons;
    }

    private ArrayList<Person> sotrByDepartment(List<Person> persons) {
        ArrayList<Person> directors = new ArrayList<>();
        ArrayList<Person> managers = new ArrayList<>();
        ArrayList<Person> others = new ArrayList<>();
        for (Person person : persons) {
            String post = person.getPost();
            if (post.contains("Руководитель")) {
                directors.add(person);
            }
            if (post.contains("Менеджер")) {
                managers.add(person);
            }
            if (!post.contains("Менеджер") && !post.contains("Руководитель")) {
                others.add(person);
            }
        }
        List<Person> personsSorted = new ArrayList<>(directors);
        personsSorted.addAll(managers);
        personsSorted.addAll(others);

        return new ArrayList<>(personsSorted);
    }
}