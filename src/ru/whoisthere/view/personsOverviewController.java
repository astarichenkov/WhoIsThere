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
import ru.whoisthere.model.PersonsData;

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
		
		PersonsData pd = new PersonsData(allPersonsData);
		gp.setColumnIndex(pd.getSecondColumn(), 0);
		/*gp.getChildren().addAll(1, pd.getSecondColumn());
		gp.getChildren().addAll(2, pd.getThirdColumn());
		gp.getChildren().addAll(3, pd.getFourthColumn());
		gp.getChildren().addAll(4, pd.getFifthColumn());
		gp.getChildren().addAll(5, pd.getSixthColumn());
		gp.getChildren().addAll(6, pd.getSeventhColumn());
		gp.getChildren().addAll(7, pd.getEighthColumn());
		gp.getChildren().addAll(8, pd.getNinthColumn());
		gp.getChildren().addAll(9, pd.getTenthColumn());
		gp.getChildren().addAll(10, pd.getEleventhColumn());
		gp.getChildren().addAll(11, pd.getTwelthColumn());
		gp.getChildren().addAll(12, pd.getThirtinthColumn());
		gp.getChildren().addAll(13, pd.getFourtinthColumn());
		gp.getChildren().addAll(14, pd.getFiftinthColumn());
		gp.getChildren().addAll(15, pd.getSixtinthColumn());*/
		
		
	}
	
	
	
	
	
	
}
