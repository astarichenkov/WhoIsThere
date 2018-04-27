package ru.whoisthere;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

public class DownloadData extends Thread{
	List<Person> persons = new ArrayList<Person>();
	int maxPersons = 0;
	DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	String dataDateTime; 
	
	@Override
	public void run () {
		Timer timer = new Timer();
		try {
			SqlUtils sqlutil = new SqlUtils();
			TimerTask timerTask = new TimerTask(){
				public void run() {
					if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
						Date refreshingStart = new Date();
						System.out.println("������. ������ �������� ������: " + refreshingStart);
						persons = sqlutil.execQuery();
						Date refreshingEnd = new Date();
						maxPersons = 0;
						
						
						Departments otdels = new Departments();
						int colCount = otdels.getOtdelsCount();
						
						for (int i = 0; i<colCount; i++) {
							int personsInOtdel = 0;
							for (Person person : persons) {	
								if (person.getDepartment().equals(otdels.getDepartmentTitle(i))) {
									personsInOtdel += 1;
								}
							}
							if (personsInOtdel > maxPersons) {
								maxPersons = personsInOtdel;
							}
						}
						System.out.println("������. ��������� ��: " + (refreshingEnd.getTime() - refreshingStart.getTime())/1000 + " ���.");
					}
				}
			};			
			timer.schedule(timerTask, 0, 30000);			
		} catch (Exception e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public List<Person> getPersons(){
		if (persons!= null) {
			return persons;
		} else {
			return null;
		}
	}
	
	public int getMaxPersons() {		
		return maxPersons;
	}
	
	public String getDataTime() {
		Date curDateTime = new Date();
		String dataTime = df.format(curDateTime);
		return dataTime;
	}
	
}
