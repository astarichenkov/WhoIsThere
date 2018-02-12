package ru.whoisthere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	
	public ArrayList<ArrayList<String>> execQuery(String queryStr){
		ArrayList<ArrayList<String>> allPersonsData = new ArrayList<ArrayList<String>>();
		
		try {
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(queryStr);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				ArrayList<String> personData = new ArrayList<String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					personData.add(rs.getString(i));
				}
				allPersonsData.add(personData);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return allPersonsData;
	}	
}
