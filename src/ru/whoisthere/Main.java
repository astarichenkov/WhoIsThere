package ru.whoisthere;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> allPersonsData = null;
		String queryStr = 	"SELECT plist.FirstName, plist.Name, p.TimeVal, p.HozOrgan, plist.Section, div.Name " +
							"FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan, Max(Mode) as mode FROM ORION1122.dbo.pLogData Where TimeVal>='20180210' Group By HozOrgan) as p " +
							"INNER JOIN (SELECT ID, Name, FirstName, Section From ORION1122.dbo.pList  where Section <> '199' and Company=2) plist ON plist.ID = p.HozOrgan " +
							"LEFT JOIN [ORION1122].[dbo].[PDivision] div ON div.ID = plist.Section " +
							"WHERE TimeVal >='20180210' and p.Mode = 1 and div.ID < 184 " +
							"Order by div.Name";
		
		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			allPersonsData = sqlutil.execQuery(queryStr);
		}
		
		System.out.println(allPersonsData);
		
	}

}
