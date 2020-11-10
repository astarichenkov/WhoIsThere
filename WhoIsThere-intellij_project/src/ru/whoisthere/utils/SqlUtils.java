package ru.whoisthere.utils;


import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executors;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;
import ru.whoisthere.model.PhotoCache;
import ru.whoisthere.settings.ConnectionSettings;
import ru.whoisthere.settings.DoorsReadersSettings;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static ru.whoisthere.utils.Logging.addInfoLog;

public class SqlUtils {
    private Departments departs = new Departments();
    private Connection con;
//    private DoorsReadersSettings doors = new DoorsReadersSettings();
    private int inputHall = DoorsReadersSettings.getInputHall();
    private int outputHall = DoorsReadersSettings.getOutputHall();
    private int inputMag = DoorsReadersSettings.getInputMag();
    private int exitDoor = DoorsReadersSettings.getExitMag();
    private List<Person> persons = Collections.synchronizedList(new ArrayList<>());
//    private ConnectionSettings settings = new ConnectionSettings();
    private final String encoding = "UTF8";

    private void downloadPhotosToCache() {
        ResultSet rs;
        BufferedImage buffer = new BufferedImage(30, 30, TYPE_INT_RGB);
        for (Person person : persons) {
            String name = person.getName();
            String surname = person.getSurname();

            try {
                rs = getPersons(name, surname);
                if (rs.next()) {
                    buffer = PhotoCache.biToImage(rs.getBytes(4));
                }
            } catch (SQLException e) {
                addInfoLog(e.getMessage());
            }
            person.setPhoto(buffer);
            PhotoCache.addPersonToCache(person);
            addInfoLog(person + " photo downloaded");
        }
        addInfoLog("***Personn photos loaded to cache***");
    }

    public boolean closeConnection() {
//        try {
//            this.con.close();
//            Logging.addInfoLog("Disconnecting from the server.");
            return true;
//        } catch (SQLException e) {
//            Logging.addWarningLog(e.getMessage());
//            return false;
        }


    public List<Person> execQuery() {
        ArrayList<String> otdels = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            otdels.add(departs.getDepartmentName(i));
        }

        String serverAddress = ConnectionSettings.getIp();
        String pathToDB = ConnectionSettings.getPathToDB();
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            this.con = DriverManager.getConnection(
                    "jdbc:firebirdsql://" + serverAddress
                            + "/" + pathToDB, getProperties());
//            Logging.addInfoLog("Connection to the server " + serverAddress + " was successful.");

            Statement stmt = con.createStatement();
            String role = "ADMIN";
            ResultSet rs = null;
            rs = getEvents();
            ResultSet rs2;

            while (rs.next()) {
                Person person = new Person(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), new BufferedImage(100, 100, TYPE_INT_RGB));
                if (person.getName() == null || person.getSurname() == null
                        || person.getDepartment() == null) {
                    continue;
                }
                int pObject = rs.getInt(5);

                if (pObject == inputMag || pObject == inputHall || pObject == outputHall) {
                    if (pObject == inputHall) {
                        person.setPresent(true);
                    } else {
                        person.setPresent(false);
                    }

                    if (!persons.contains(person) && otdels.contains(person.getDepartment())) {
                        persons.add(person);
                    } else if (otdels.contains(person.getDepartment())) {
                        persons.remove(person);
                        persons.add(person);
                    }
                    continue;
                }

                if (pObject == exitDoor) {
                    persons.remove(person);
                }
            }

            if (PhotoCache.isEmpty()) {
                downloadPhotosToCache();
                PhotoCache.load(false);
            }

            for (Person person : persons) {
                String name = person.getName();
                String surname = person.getSurname();
                BufferedImage buffer = new BufferedImage(100, 100, TYPE_INT_RGB);

                if (PhotoCache.personsCacheContains(person)) {
                    Person buffPerson = PhotoCache.getPersonFromCache(person);
                    person.setPhoto(buffPerson.getPhoto());
                } else {
                    rs2 = getPersons(name, surname);
                    if (rs2.next()) {
                        buffer = PhotoCache.biToImage(rs2.getBytes(4));
                    }
                    person.setPhoto(buffer);
                    PhotoCache.addPersonToCache(person);
                    addInfoLog(person + " photo downloaded");
                }
            }
            rs.close();
            stmt.close();
            addInfoLog("Employee data is received. " + persons.size() + " records.");
        } catch (SQLException | ClassNotFoundException | NullPointerException e) {
            Logging.addWarningLog(e.getMessage());
        } finally {
            closeConnection();
        }
        return sortByPresent(sortByDepartment(persons));
    }

    private ArrayList<Person> sortByDepartment(List<Person> persons) {
        ArrayList<Person> directors = new ArrayList<>();
        ArrayList<Person> managers = new ArrayList<>();
        ArrayList<Person> others = new ArrayList<>();
        for (Person person : persons) {
            String post = person.getPost();
            if (post == null) {
                continue;
            }
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


    private ArrayList<Person> sortByPresent(List<Person> persons) {
        Comparator<Person> comparator = Comparator.comparing(Person::isPresent);
        persons.sort(comparator.reversed());
        return new ArrayList<>(persons);
    }


    private ResultSet getEvents() throws SQLException {
        String queryStr = "SELECT events.lname1, events.lname2, events.ldepartment,"
                + " EVENTS.LPOST, EVENTS.POBJECT, EVENTS.T"
                + " from events where ((d = (SELECT MAX(D) FROM EVENTS) )"
                + " AND (POBJECT = ? OR POBJECT = ? OR POBJECT = ? OR POBJECT = ?)) ORDER BY LNAME1, T ASC";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(queryStr);
            preparedStatement.setInt(1, inputHall);
            preparedStatement.setInt(2, outputHall);
            preparedStatement.setInt(3, inputMag);
            preparedStatement.setInt(4, exitDoor);
        } catch (SQLException e) {
            addInfoLog(e.getMessage());
        }
        return preparedStatement.executeQuery();
    }

    private ResultSet getPersons(String name, String surname) throws SQLException {
        String queryStr = "SELECT DISTINCT events.lname1, events.lname2, events.ldepartment,"
                + " CARDS.PHOTO, EVENTS.POBJECT from events JOIN CARDS ON"
                + " (EVENTS.LNAME1 = CARDS.NAME1 AND EVENTS.LNAME2 = CARDS.NAME2) where"
                + " (d = (SELECT MAX(D) FROM EVENTS) AND "
                + " (POBJECT = ? OR POBJECT = ? OR POBJECT = ? OR POBJECT = ?)"
                + " AND EVENTS.LNAME1 = ?"
                + " AND EVENTS.LNAME2 = ?"
                + " AND CARDS.PHOTO IS NOT NULL)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(queryStr);
            preparedStatement.setInt(1, inputHall);
            preparedStatement.setInt(2, outputHall);
            preparedStatement.setInt(3, inputMag);
            preparedStatement.setInt(4, exitDoor);
            preparedStatement.setString(5, name);
            preparedStatement.setString(6, surname);
        } catch (SQLException e) {
            addInfoLog(e.getMessage());
        }
        return preparedStatement.executeQuery();
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("user", ConnectionSettings.getLogin());
        byte[] decodedBytes = Base64.getDecoder().decode(ConnectionSettings.getAsswd());
        String decodedAsswd = new String(decodedBytes);
        props.setProperty("password", decodedAsswd);
        props.setProperty("encoding", encoding);
        return props;
    }
}