package ru.whoisthere;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.whoisthere.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtils {
	
	private Connection con;
	private ArrayList<ArrayList<String>> allPersonsData = new ArrayList<ArrayList<String>>();
	private List<Person> persons = new ArrayList<Person>(); 

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
	
	public List<Person> getResultArray(){
		String queryStr = 	"SELECT plist.FirstName, plist.Name, p.TimeVal, p.HozOrgan, plist.Section, div.Name, plist.Picture " +
				"FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan, Max(Mode) as mode FROM ORION1122.dbo.pLogData Where TimeVal>='20180224' Group By HozOrgan) as p " +
				"INNER JOIN (SELECT ID, Name, FirstName, Section, Picture From ORION1122.dbo.pList  where Section <> '199' and Company=2) plist ON plist.ID = p.HozOrgan " +
				"LEFT JOIN [ORION1122].[dbo].[PDivision] div ON div.ID = plist.Section " +
				"WHERE TimeVal >='20180224' and p.Mode = 1 and div.ID < 184 " +
				"Order by div.Name";		
		try {
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				Person pers = new Person(rs.getString(1), rs.getString(2), rs.getString(6), rs.getBytes(7));				
				persons.add(pers);	
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
	
	public int getColumnCount() {
		try {
			return allPersonsData.get(0).size();
		} catch(Exception e) {
			return 0;
		}
		
	}
	
	public void getImageData(Connection con) {
		byte[] fileBytes;
	    String query;
	    try
	    {
	            query = "select top 1 Picture From ORION1122.dbo.plist Where Picture not null";
	            Statement state = con.createStatement();
	            ResultSet rs = state.executeQuery(query);
	            if (rs.next())
	           {
	                     fileBytes = rs.getBytes(1);
	                     OutputStream targetFile= new FileOutputStream("c://filepath//new.JPG");
	
	                     targetFile.write(fileBytes);
	                     targetFile.close();
	           }                      
	    }
	    catch (Exception e)
	    {
	            e.printStackTrace();
	    }
	}
}
