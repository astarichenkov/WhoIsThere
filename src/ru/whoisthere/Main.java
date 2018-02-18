package ru.whoisthere;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	static ArrayList<ArrayList<String>> allPersonsData = null;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setFullScreen(true);
		primaryStage.setTitle("Кто в Зале!?");
		
		Parent layout = FXMLLoader.load(getClass().getResource("view/personsOverview.fxml"));
		
		Scene scene = new Scene (layout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
