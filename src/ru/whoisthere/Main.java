package ru.whoisthere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
			
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<List<String>> otdels = new ArrayList<List<String>>();
		
		otdels.add(Arrays.asList(new String[]{"дирекция", "дирекция"}));
		otdels.add(Arrays.asList(new String[]{"строительные материалы", "1 отдел"}));
		otdels.add(Arrays.asList(new String[]{"столярные изделия", "2 отдел"}));
		otdels.add(Arrays.asList(new String[]{"электротовары", "3 отдел"}));
		otdels.add(Arrays.asList(new String[]{"инструменты", "4 отдел"}));
		otdels.add(Arrays.asList(new String[]{"напольные покрытия", "5 отдел"}));
		otdels.add(Arrays.asList(new String[]{"настенная и напольная плитка", "6 отдел"}));
		otdels.add(Arrays.asList(new String[]{"сантехника", "7 отдел"}));
		otdels.add(Arrays.asList(new String[]{"водоканализационные системы - отопление", "8 отдел"}));
		otdels.add(Arrays.asList(new String[]{"сад", "9 отдел"}));
		otdels.add(Arrays.asList(new String[]{"скобяные изделия", "10 отдел"}));
		otdels.add(Arrays.asList(new String[]{"лакокрасочные изделия", "11 отдел"}));
		otdels.add(Arrays.asList(new String[]{"отделочные материалы", "12 отдел"}));
		otdels.add(Arrays.asList(new String[]{"освещение", "13 отдел"}));
		otdels.add(Arrays.asList(new String[]{"обустройство дома", "14 отдел"}));
		otdels.add(Arrays.asList(new String[]{"кухни", "15 отдел"}));
		
		AnchorPane mainPane = new AnchorPane();
		TableView tv = new TableView();		
		mainPane.setTopAnchor(tv, 0.0);
		mainPane.setRightAnchor(tv, 0.0);
		mainPane.setLeftAnchor(tv, 0.0);
		mainPane.setBottomAnchor(tv, 0.0);
		
		
		int columnsCount = otdels.size();
		for (int i=0; i<columnsCount; i++) {
			TableColumn tc = new TableColumn(otdels.get(i).get(1));
			tc.prefWidthProperty().bind(tv.widthProperty().divide(columnsCount));
			tv.getColumns().add(tc);
		}
		
		primaryStage.setFullScreen(true);
		primaryStage.setTitle("Кто в Зале!?");		
		mainPane.getChildren().add(tv);		
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
