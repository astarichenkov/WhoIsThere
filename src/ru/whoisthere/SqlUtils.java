package ru.whoisthere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.whoisthere.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtils {
	
	private Connection con;
	
	public boolean openConnection(String serverAddress, String login, String password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://" + serverAddress;
			this.con = DriverManager.getConnection(url, login, password);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean closeConnection() {
		try {
			this.con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Person> execQuery(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String curDate = dateFormat.format(new Date()); 
	
		List<Person> persons = new ArrayList<Person>();
/*		String queryStr = 	"SELECT plist.FirstName, plist.Name, div.Name, plist.Picture " +
				"FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan, Max(Mode) as mode FROM ORION1122.dbo.pLogData Where TimeVal>='" + curDate + "' Group By HozOrgan) as p " +
				"INNER JOIN (SELECT ID, Name, FirstName, Section, Picture From ORION1122.dbo.pList  where Section <> '199' and Company=2) plist ON plist.ID = p.HozOrgan " +
				"LEFT JOIN [ORION1122].[dbo].[PDivision] div ON div.ID = plist.Section " +
				"WHERE TimeVal >='" + curDate + "' and p.Mode = 1 and div.ID < 184 " +
				"Order by plist.Name";*/
		String queryStr = "SELECT pList.FirstName, pList.Name, pDiv.Name, pList.Picture " +
				"FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan FROM ORION1122.dbo.pLogData Where TimeVal>='" + curDate + "' and Event='32' and DoorIndex='15' Group by HozOrgan) as pLog " +
				"INNER JOIN (SELECT TimeVal, HozOrgan, Mode From ORION1122.dbo.pLogData Where Event='32' and DoorIndex='15' and TimeVal>='" + curDate + "' and Mode=1) maxMode ON maxMode.HozOrgan = pLog.HozOrgan and maxMode.TimeVal = pLog.TimeVal " +
				"INNER JOIN (SELECT ID, Name, FirstName, Section, Picture From ORION1122.dbo.pList where TabNumber <> '' and Company=2) pList ON pList.ID = pLog.HozOrgan " +
				"INNER JOIN (SELECT ID, Name FROM [ORION1122].[dbo].[PDivision] Where GroupID='1') pDiv ON pDiv.ID = pList.Section " +
				"Order By maxMode.Mode";
		try {
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				Person person = new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBytes(4));
				persons.add(person);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return persons;
	}	
}
