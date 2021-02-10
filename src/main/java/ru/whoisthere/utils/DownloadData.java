package ru.whoisthere.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class DownloadData {
    private Departments otdels = new Departments();
    private List<Person> persons = Collections.synchronizedList(new ArrayList<>());
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private boolean dataDownloaded = false;
    private int maxPersons = 0;

//    public int getMaxPersons() {
//        return maxPersons;
//    }

    public String getDataTime() {
        if (dataDownloaded) {
            Date curDateTime = new Date();
            String dataTime = df.format(curDateTime);
            return dataTime;
        } else {
            return "no data";
        }

    }

    public void run() {
        SqlUtils sqlutil = new SqlUtils();
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
        addInfoLog("Data is loaded in "
                + (refreshingEnd.getTime() - refreshingStart.getTime())
                + " ms.");
    }

    public List<Person> getPersons() {
        run();
        if (persons != null) {
            return persons;
        } else {
            return null;
        }
    }
}
