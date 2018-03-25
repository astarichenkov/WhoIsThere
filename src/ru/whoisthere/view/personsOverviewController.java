package ru.whoisthere.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.whoisthere.SqlUtils;
import ru.whoisthere.model.Person;


public class personsOverviewController {
	private List<Person> persons = new ArrayList<Person>();
	
	@FXML
	public Button btn;
	public GridPane gp = new GridPane();
/*	VBox col0 = new VBox(new Label("sdasdsd"));
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
	VBox col15 = new VBox();*/	
	
	@FXML
	public void uploadData() {
		
		clearData();
		
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
		int maxPersons = 0;
		int personsInOtdel = 0;
		/*for (int i = 0; i<colCount; i++) {
			for (int j = 0; j<colCount; j++) {
				if ()
			}
		}*/
		
		
		for (Person person : persons) {	
			for (int i = 0; i<colCount; i++) {
				if (person.getDepartment().equals(otdels.get(i).get(0))) {
					
					String nodeName = "col" + i;
					VBox mynode = (VBox) gp.lookup("#" + nodeName);
					
					System.out.println(mynode.getHeight());
					
					//------------ элемент с фотографией--------------------------------
					ImageView personPhoto = new ImageView(SwingFXUtils.toFXImage(person.getPhoto(), null));	
					personPhoto.setFitWidth(mynode.getWidth());
					personPhoto.setPreserveRatio(true);
					//==================================================================
					
					//------------ элемент с именем и фамилией--------------------------
					VBox personName = new VBox();
					personName.setAlignment(Pos.TOP_CENTER);
					personName.getChildren().addAll(new Label(person.getName()), new Label(person.getSurname()));					
					//==================================================================
					
					//------------- контейнер с выводимой в столбец информацией о сотруднике---
					VBox personInfoContainer = new VBox();
					personInfoContainer.setAlignment(Pos.TOP_CENTER);
					personInfoContainer.setMaxWidth(250.0);
					personInfoContainer.getChildren().addAll(personPhoto, personName);
					//=========================================================================
					
					mynode.getChildren().add(personInfoContainer);
				}
			}
		}		
	}
	
	private void clearData() {
		for (Node n: gp.getChildren()) {
			if (n.getStyleClass().toString().equals("columns")) {
				VBox node = (VBox) n;
				node.getChildren().clear();
			}
		}
	}
}
