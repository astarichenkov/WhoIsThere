package ru.whoisthere.view;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.whoisthere.SqlUtils;

public class personsOverviewController {
	
	static ArrayList<ArrayList<String>> allPersonsData = null;
	public GridPane gp = new GridPane();
	
	@FXML
	public Button btn;

	public void uploadData() {
		
		String queryStr = 	"SELECT plist.FirstName, plist.Name, p.TimeVal, p.HozOrgan, plist.Section, div.Name, plist.Picture " +
				"FROM (SELECT MAX(TimeVal) as TimeVal, HozOrgan, Max(Mode) as mode FROM ORION1122.dbo.pLogData Where TimeVal>='20180213' Group By HozOrgan) as p " +
				"INNER JOIN (SELECT ID, Name, FirstName, Section, Picture From ORION1122.dbo.pList  where Section <> '199' and Company=2) plist ON plist.ID = p.HozOrgan " +
				"LEFT JOIN [ORION1122].[dbo].[PDivision] div ON div.ID = plist.Section " +
				"WHERE TimeVal >='20180213' and p.Mode = 1 and div.ID < 184 " +
				"Order by div.Name";

		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			allPersonsData = sqlutil.execQuery(queryStr);
		}
		
		VBox vb = new VBox();
		vb.setAlignment(Pos.TOP_CENTER);
		for (int i=0; i < allPersonsData.size(); i++) {
			
			VBox p = new VBox();
			p.setStyle("-fx-border-color:black");
			p.getChildren().add(new Label(allPersonsData.get(i).get(0)));
			p.getChildren().add(new Label(allPersonsData.get(i).get(1)));
			vb.getChildren().add(p);			
		}
		gp.add(vb, 1, 1);
		
		
		
		
	}
	
	
	
	
	
	
}
