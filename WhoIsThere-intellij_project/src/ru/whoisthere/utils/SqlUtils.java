package ru.whoisthere.utils;


import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;
import ru.whoisthere.model.PhotoCache;
import ru.whoisthere.settings.ConnectionSettings;
import ru.whoisthere.settings.DoorsReadersSettings;

import java.util.Properties;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class SqlUtils {
    private static Loging logs = new Loging();
    private Departments departs = new Departments();
    private Connection con;
    private DoorsReadersSettings doors = new DoorsReadersSettings();
    private int inputDoor = doors.getInputHall();
    private int outputDoor = doors.getOutputHall();
    private int exitDoor = doors.getExitMag();
    private List<Person> persons = new ArrayList<>();
    private ConnectionSettings settings = new ConnectionSettings();
    private final String encoding = "UTF8";

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
        ArrayList<String> otdels = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            otdels.add(departs.getDepartmentName(i));
        }

        String serverAddress = settings.getIp();
        String pathToDB = settings.getPathToDB();
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            this.con = DriverManager.getConnection(
                    "jdbc:firebirdsql://" + serverAddress
                            + "/" + pathToDB, getProperties());
            logs.addInfoLog("Connection to the server " + serverAddress + " was successful.");

            Statement stmt = con.createStatement();
//            String host = InetAddress.getLocalHost().getCanonicalHostName();
            String role = "";
//            if (host.contains("leroymerlin")) {
                role = "ADMIN";
//            }
            ResultSet rs = null;
//            if (role.equals("ADMIN")) {
                rs = getEvents();
//            }

            while (rs.next()) {
                Person person = new Person(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), new BufferedImage(100, 100, TYPE_INT_RGB));
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
                    persons.remove(person);
                }
            }

            for (Person person : persons) {
                String name = person.getName();
                String surname = person.getSurname();

                if (PhotoCache.personsCacheContains(person)) {
                    Person buffPerson = PhotoCache.getPersonFromCache(person);
                    person.setPhoto(buffPerson.getPhoto());
//                    System.out.println(person + "фото уже есть");
                    continue;
                }

//                if (role.equals("ADMIN")) {
                    rs = getPersons(name, surname);
//                }
                if (rs.next()) {
                    BufferedImage buffer = PhotoCache.biToImage(rs.getBytes(4));
                    person.setPhoto(buffer);
                    PhotoCache.addPersonToCache(person);
                    System.out.println(person + "фото загружено");

                }
            }

            rs.close();
            stmt.close();
            logs.addInfoLog("Employee data is received. " + persons.size() + " records.");
        } catch (SQLException | ClassNotFoundException | NullPointerException e) {
            logs.addWarningLog(e.getMessage());
        } finally {
            closeConnection();
        }

        return persons;
    }

    private ResultSet getEvents() throws SQLException {
        String queryStr = "SELECT events.lname1, events.lname2, events.ldepartment,"
                + " EVENTS.LPOST, EVENTS.POBJECT"
                + " from events where ((d = (SELECT MAX(D) FROM EVENTS) )"
                + " AND (POBJECT = ? OR POBJECT = ? OR POBJECT = ?)) ORDER BY LNAME1, T ASC";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(queryStr);
            preparedStatement.setInt(1, inputDoor);
            preparedStatement.setInt(2, outputDoor);
            preparedStatement.setInt(3, exitDoor);
        } catch (SQLException e) {
            logs.addInfoLog(e.getMessage());
        }
        return preparedStatement.executeQuery();
    }

    private ResultSet getPersons(String name, String surname) throws SQLException {
        String queryStr = "SELECT DISTINCT events.lname1, events.lname2, events.ldepartment,"
                + " CARDS.PHOTO, EVENTS.POBJECT from events JOIN CARDS ON"
                + " (EVENTS.LNAME1 = CARDS.NAME1 AND EVENTS.LNAME2 = CARDS.NAME2) where"
                + " (d = (SELECT MAX(D) FROM EVENTS) AND "
                + " (POBJECT = ? OR POBJECT = ?)"
                + " AND EVENTS.LNAME1 = ?"
                + " AND EVENTS.LNAME2 = ?"
                + " AND CARDS.PHOTO IS NOT NULL)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(queryStr);
            preparedStatement.setInt(1, inputDoor);
            preparedStatement.setInt(2, exitDoor);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
        } catch (SQLException e) {
            logs.addInfoLog(e.getMessage());
        }
        return preparedStatement.executeQuery();
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("user", settings.getLogin());
        byte[] decodedBytes = Base64.getDecoder().decode(settings.getAsswd());
        String decodedAsswd = new String(decodedBytes);
        props.setProperty("password", decodedAsswd);
        props.setProperty("encoding", encoding);
        return props;
    }

    public Person getPersonFromCache(Person person) {
        return PhotoCache.getPersonFromCache(person);
    }

}