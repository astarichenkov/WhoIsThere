package ru.whoisthere.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.whoisthere.SqlUtils;
import ru.whoisthere.model.Person;

public class personsOverviewController {
	
	private List<Person> persons = new ArrayList<Person>();
	public GridPane gp = new GridPane();

	
	
	
	@FXML
	public Button btn;
	
	@FXML
	public VBox firstColumn = new VBox();
	public VBox secondColumn = new VBox();
	public VBox thirdColumn = new VBox();
	public VBox fourthColumn = new VBox();
	public VBox fifthColumn = new VBox();
	public VBox sixthColumn = new VBox();
	public VBox seventhColumn = new VBox();
	public VBox eighthColumn = new VBox();
	public VBox ninthColumn = new VBox();
	public VBox tenthColumn = new VBox();
	public VBox eleventhColumn = new VBox();
	public VBox twelfthColumn = new VBox();
	public VBox thirteenthColumn = new VBox();
	public VBox fourteenthColumn = new VBox();
	public VBox fifteenthColumn = new VBox();
	public VBox sixteenthColumn = new VBox();
	
	

	public void uploadData(){		
		//gp.getColumnConstraints().clear();
		
		SqlUtils sqlutil = new SqlUtils();
		if (sqlutil.openConnection("10.84.79.125", "sa", "123456")) {
			persons = sqlutil.getResultArray();
		}
		
		
		for (Person person : persons) {
			VBox personInfoContainer = new VBox();
			ImageView iv = new ImageView();
			Image image = SwingFXUtils.toFXImage(person.getPhoto(), null);
			
			iv.setFitWidth(100);
			iv.setFitHeight(150);
			iv.setImage(image);
			
			personInfoContainer.getChildren().add(iv);
			personInfoContainer.getChildren().add(new Label (person.getName()));
			personInfoContainer.getChildren().add(new Label (person.getSurname()));
			personInfoContainer.setAlignment(Pos.CENTER);
		
		gp.getChildren().add(0, firstColumn);
		}	
	}
}

