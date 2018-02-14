package ru.whoisthere;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class Main extends Application implements EventHandler<ActionEvent>{

	Button button;
	static ArrayList<ArrayList<String>> allPersonsData = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String queryStr = 	"SELECT plist.FirstName, plist.Name, p.TimeVal, p.HozOrgan, plist.Section, div.Name " +
							"FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan, Max(Mode) as mode FROM ORION1122.dbo.pLogData Where TimeVal>='20180213' Group By HozOrgan) as p " +
							"INNER JOIN (SELECT ID, Name, FirstName, Section From ORION1122.dbo.pList  where Section <> '199' and Company=2) plist ON plist.ID = p.HozOrgan " +
							"LEFT JOIN [ORION1122].[dbo].[PDivision] div ON div.ID = plist.Section " +
							"WHERE TimeVal >='20180213' and p.Mode = 1 and div.ID < 184 " +
							"Order by div.Name";
		
		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			allPersonsData = sqlutil.execQuery(queryStr);
		}
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setFullScreen(true);
		primaryStage.setTitle("Кто в Зале!?");
		
		Parent layout = FXMLLoader.load(getClass().getResource("view/WhoIsThereOverview.fxml"));
		
		Scene scene = new Scene (layout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == button) {
			System.out.println(allPersonsData);
		}
	}
	

}
