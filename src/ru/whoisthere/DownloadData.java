package ru.whoisthere;

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
	@Override
	public void run () {
		Timer timer = new Timer();
		try {
			SqlUtils sqlutil = new SqlUtils();
			TimerTask timerTask = new TimerTask(){
				public void run() {
					if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
						Date refreshingStart = new Date();
						System.out.println("Начало обработки: " + refreshingStart);
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
							try {
								
								Thread.sleep(100);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						System.out.println("Данные загружены за: " + (refreshingEnd.getTime() - refreshingStart.getTime())/1000 + " сек.");
					}
				}
			};			
			timer.schedule(timerTask, 0, 15000);			
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
	
	
}
