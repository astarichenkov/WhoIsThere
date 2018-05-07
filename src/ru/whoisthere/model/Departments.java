package ru.whoisthere.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Departments {
	List<List<String>> departs = new ArrayList<List<String>>();
	
	public Departments() {
		departs.add(Arrays.asList((new String[]{"дирекци€", "дирекци€"})));
		departs.add(Arrays.asList((new String[]{"строительные материалы", "1 отдел"})));
		departs.add(Arrays.asList((new String[]{"стол€рные издели€", "2 отдел"})));
		departs.add(Arrays.asList((new String[]{"электротовары", "3 отдел"})));
		departs.add(Arrays.asList((new String[]{"инструменты", "4 отдел"})));
		departs.add(Arrays.asList((new String[]{"напольные покрыти€", "5 отдел"})));
		departs.add(Arrays.asList((new String[]{"настенна€ и напольна€ плитка", "6 отдел"})));
		departs.add(Arrays.asList((new String[]{"сантехника", "7 отдел"})));
		departs.add(Arrays.asList((new String[]{"водоканализационные системы - отопление", "8 отдел"})));
		departs.add(Arrays.asList((new String[]{"сад", "9 отдел"})));
		departs.add(Arrays.asList((new String[]{"скоб€ные издели€", "10 отдел"})));
		departs.add(Arrays.asList((new String[]{"лакокрасочные издели€", "11 отдел"})));
		departs.add(Arrays.asList((new String[]{"отделочные материалы", "12 отдел"})));
		departs.add(Arrays.asList((new String[]{"освещение", "13 отдел"})));
		departs.add(Arrays.asList((new String[]{"обустройство дома", "14 отдел"})));
		departs.add(Arrays.asList((new String[]{"кухни", "15 отдел"})));
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
