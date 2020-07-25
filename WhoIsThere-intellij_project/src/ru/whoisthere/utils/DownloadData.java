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
import ru.whoisthere.settings.ConnectionSettings;
import ru.whoisthere.utils.Loging;
import ru.whoisthere.utils.SqlUtils;

public class DownloadData extends Thread {
    private static Loging logs = new Loging();
    private Departments otdels = new Departments();
    private List<Person> persons = new ArrayList<Person>();
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private String dataDateTime;
    private boolean dataDownloaded = false;
    private ConnectionSettings settings = new ConnectionSettings();
    private int maxPersons = 0;

    @Override
    public void run() {
        Timer timer = new Timer();
        try {
            SqlUtils sqlutil = new SqlUtils();
            TimerTask timerTask = new TimerTask() {
                public void run() {

                    if (sqlutil.openConnection(settings.getIp(), settings.getLogin(),
                            settings.getPassword(), settings.getPathToDB())) {
                        Date refreshingStart = new Date();
                        persons = sqlutil.execQuery();
                        Date refreshingEnd = new Date();
                        int colCount = otdels.getOtdelsCount();

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

                        dataDownloaded = true; // признак, что данные загрузились
                        logs.addInfoLog("Data is loaded in "
                                + (refreshingEnd.getTime() - refreshingStart.getTime()) / 1000
                                + " seconds.");
                    }
                }
            };
            timer.schedule(timerTask, 0, 40000);

        } catch (Exception e) {
            dataDownloaded = false;
            logs.addWarningLog(e.getMessage());
        }
    }

    public List<Person> getPersons() {
        if (persons != null) {
            return persons;
        } else {
            return null;
        }
    }

    public int getMaxPersons() {
        //logs.setInfoLog("Максимальное число сотрудников в отделе: " + maxPersons);
        return maxPersons;
    }

    public String getDataTime() {
        if (dataDownloaded) { // если данные загрузились, то
            Date curDateTime = new Date();
            String dataTime = df.format(curDateTime);
            return dataTime;
        } else {
            return null;
        }

    }

}