package ru.whoisthere.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.whoisthere.SqlUtils;
import ru.whoisthere.model.Person;


public class personsOverviewController {
	private List<Person> persons = new ArrayList<Person>();
	
	@FXML
	public Button btn;
	public GridPane gp = new GridPane();
	VBox col0 = new VBox(new Label("sdasdsd"));
	VBox col1 = new VBox();
	VBox col2 = new VBox();
	VBox col3 = new VBox();
	VBox col4 = new VBox();
	VBox col5 = new VBox();
	VBox col6 = new VBox();
	VBox col7 = new VBox();
	VBox col8 = new VBox();
	VBox col9 = new VBox();
	VBox col10 = new VBox();
	VBox col11 = new VBox();
	VBox col12 = new VBox();
	VBox col13 = new VBox();
	VBox col14 = new VBox();
	VBox col15 = new VBox();	
	
	@FXML
	public void uploadData() {
		List<List<String>> otdels = new ArrayList<List<String>>();		
		otdels.add(Arrays.asList((new String[]{"дирекци€", "дирекци€"})));
		otdels.add(Arrays.asList((new String[]{"строительные материалы", "1 отдел"})));
		otdels.add(Arrays.asList((new String[]{"стол€рные издели€", "2 отдел"})));
		otdels.add(Arrays.asList((new String[]{"электротовары", "3 отдел"})));
		otdels.add(Arrays.asList((new String[]{"инструменты", "4 отдел"})));
		otdels.add(Arrays.asList((new String[]{"напольные покрыти€", "5 отдел"})));
		otdels.add(Arrays.asList((new String[]{"настенна€ и напольна€ плитка", "6 отдел"})));
		otdels.add(Arrays.asList((new String[]{"сантехника", "7 отдел"})));
		otdels.add(Arrays.asList((new String[]{"водоканализационные системы - отопление", "8 отдел"})));
		otdels.add(Arrays.asList((new String[]{"сад", "9 отдел"})));
		otdels.add(Arrays.asList((new String[]{"скоб€ные издели€", "10 отдел"})));
		otdels.add(Arrays.asList((new String[]{"лакокрасочные издели€", "11 отдел"})));
		otdels.add(Arrays.asList((new String[]{"отделочные материалы", "12 отдел"})));
		otdels.add(Arrays.asList((new String[]{"освещение", "13 отдел"})));
		otdels.add(Arrays.asList((new String[]{"обустройство дома", "14 отдел"})));
		otdels.add(Arrays.asList((new String[]{"кухни", "15 отдел"})));
				
		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			persons = sqlutil.execQuery();
		}		
		
		int colCount = otdels.size();
		for (Person person : persons) {			
			for (int i = 0; i<colCount; i++) {
				if (person.getDepartment() == otdels.get(i).get(0)) {
					String nodeName = "col" + i;
					Node node = Parent.
				}
			}	
		}		
	}	
}
