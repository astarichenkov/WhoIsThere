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
		
		otdels.add(Arrays.asList(new String[]{"��������", "��������"}));
		otdels.add(Arrays.asList(new String[]{"������������ ���������", "1 �����"}));
		otdels.add(Arrays.asList(new String[]{"��������� �������", "2 �����"}));
		otdels.add(Arrays.asList(new String[]{"�������������", "3 �����"}));
		otdels.add(Arrays.asList(new String[]{"�����������", "4 �����"}));
		otdels.add(Arrays.asList(new String[]{"��������� ��������", "5 �����"}));
		otdels.add(Arrays.asList(new String[]{"��������� � ��������� ������", "6 �����"}));
		otdels.add(Arrays.asList(new String[]{"����������", "7 �����"}));
		otdels.add(Arrays.asList(new String[]{"������������������� ������� - ���������", "8 �����"}));
		otdels.add(Arrays.asList(new String[]{"���", "9 �����"}));
		otdels.add(Arrays.asList(new String[]{"�������� �������", "10 �����"}));
		otdels.add(Arrays.asList(new String[]{"������������� �������", "11 �����"}));
		otdels.add(Arrays.asList(new String[]{"���������� ���������", "12 �����"}));
		otdels.add(Arrays.asList(new String[]{"���������", "13 �����"}));
		otdels.add(Arrays.asList(new String[]{"������������ ����", "14 �����"}));
		otdels.add(Arrays.asList(new String[]{"�����", "15 �����"}));
		
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
		primaryStage.setTitle("��� � ����!?");		
		mainPane.getChildren().add(tv);		
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
