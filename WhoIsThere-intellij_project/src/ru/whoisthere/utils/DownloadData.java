package ru.whoisthere.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

public class DownloadData extends Thread {
    private static Loging logs = new Loging();
    private Departments otdels = new Departments();
    private List<Person> persons = new ArrayList<Person>();
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private boolean dataDownloaded = false;
    private int maxPersons = 0;

    public int getMaxPersons() {
        return maxPersons;
    }

    public String getDataTime() {
        if (dataDownloaded) {
            Date curDateTime = new Date();
            String dataTime = df.format(curDateTime);
            return dataTime;
        } else {
            return "no data";
        }

    }

    @Override
    public void run() {
        Timer timer = new Timer();
            SqlUtils sqlutil = new SqlUtils();
            TimerTask timerTask = new TimerTask() {
                public void run() {

                        Date refreshingStart = new Date();
                        persons = sqlutil.execQuery();
                        Date refreshingEnd = new Date();
                        int colCount = 16;

                        for (int i = 0; i < colCount; i++) {
                            int personsInOtdel = 0;
                            for (Person person : persons) {
                                if (person.getDepartment().equals(otdels.getDepartmentName(i))) {
                                    personsInOtdel += 1;
                                }
                            }
                            if (personsInOtdel > maxPersons) {
                                maxPersons = personsInOtdel;
                            }
                        }

                        dataDownloaded = true;
                        logs.addInfoLog("Data is loaded in "
                                + (refreshingEnd.getTime() - refreshingStart.getTime()) / 1000
                                + " seconds.");
                }
            };
            timer.schedule(timerTask, 0, 20000);
            dataDownloaded = false;
    }

    public List<Person> getPersons() {
        if (persons != null) {
            return persons;
        } else {
            return null;
        }
    }
}