package ru.whoisthere.view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ru.whoisthere.SqlUtils;
import ru.whoisthere.model.Person;

public class personsOverviewController {
	
	private List<Person> persons = new ArrayList<Person>();
	public GridPane gp = new GridPane();
	
	@FXML
	public Button btn;

	public void uploadData() {
		
		

		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			persons = sqlutil.execQuery();
		}
		
		for (Person person : persons) {
			System.out.println(person.getPhoto());
		}		
	}	
}
