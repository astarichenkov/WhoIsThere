package ru.whoisthere.model;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PersonsData {
	
	private ArrayList<ArrayList<String>> personsDataAsArray;	
	private ObservableList<Node> AllColumns = FXCollections.observableArrayList();
	private ObservableList<Node> firstColumn = FXCollections.observableArrayList();
	private ObservableList<Node> secondColumn = FXCollections.observableArrayList();
	private ObservableList<Node> thirdColumn = FXCollections.observableArrayList();
	private ObservableList<Node> fourthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> fifthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> sixthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> seventhColumn = FXCollections.observableArrayList();
	private ObservableList<Node> eighthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> ninthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> tenthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> eleventhColumn = FXCollections.observableArrayList();
	private ObservableList<Node> twelfthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> thirtinthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> fourteenthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> fifteenthColumn = FXCollections.observableArrayList();
	private ObservableList<Node> sixteenthColumn = FXCollections.observableArrayList();
	private List<String> otdels = Arrays.asList(new String[] {"дирекция",
										"строительные материалы",
										"столярные изделия",
										"электротовары",
										"инструменты",
										"напольные покрытия",
										"настенная и напольная плитка",
										"сантехника",
										"водоканализационные системы - отопление",
										"сад",
										"скобяные изделия",
										"лакокрасочные изделия",
										"отделочные материалы",
										"освещение",
										"обустройство дома",
										"кухни"});
	
	
	
	public PersonsData(ArrayList<ArrayList<String>> al) {
		personsDataAsArray = al;
		handleData();
	}
	
	public ArrayList<ArrayList<String>> getPersonsData(){
		return personsDataAsArray; 
	}
	
	private void handleData() {			
		if (personsDataAsArray != null) {
			for (int i = 0; i<personsDataAsArray.size(); i ++) {
				VBox p = new VBox();
				p.setStyle("-fx-border-color:black");
				p.getChildren().add(new Label(personsDataAsArray.get(i).get(0)));
				p.getChildren().add(new Label(personsDataAsArray.get(i).get(1)));
				String otdel = personsDataAsArray.get(i).get(5);
				switch (otdels.indexOf(otdel)){
				case 0:
					firstColumn.add(p);
					break;
				case 1:
					secondColumn.add(p);
					break;
				case 2:
					thirdColumn.add(p);
					break;
				case 3:					
					fourthColumn.add(p);
					break;
				case 4:
					fifthColumn.add(p);
					break;
				case 5:					
					sixthColumn.add(p);
					break;
				case 6:					
					seventhColumn.add(p);
					break;
				case 7:					
					eighthColumn.add(p);
					break;
				case 8:					
					ninthColumn.add(p);
					break;
				case 9:					
					tenthColumn.add(p);
					break;
				case 10:				
					eleventhColumn.add(p);
					break;
				case 11:					
					twelfthColumn.add(p);
					break;
				case 12:				
					thirtinthColumn.add(p);
					break;
				case 13:					
					fourteenthColumn.add(p);
					break;
				case 14:					
					fifteenthColumn.add(p);
					break;
				case 15:					
					sixteenthColumn.add(p);
					break;
				}				
			}			
		}		
	}
	
	public ObservableList<Node> getAllColumns(){
		AllColumns.addAll(this.getFirstColumn());
		AllColumns.addAll(this.getSecondColumn());
		AllColumns.addAll(this.getThirdColumn());
		AllColumns.addAll(this.getFourthColumn());
		AllColumns.addAll(this.getFifthColumn());
		AllColumns.addAll(this.getSixthColumn());
		AllColumns.addAll(this.getSeventhColumn());
		AllColumns.addAll(this.getEighthColumn());
		AllColumns.addAll(this.getNinthColumn());
		AllColumns.addAll(this.getTenthColumn());
		AllColumns.addAll(this.getEleventhColumn());
		AllColumns.addAll(this.getTwelthColumn());
		AllColumns.addAll(this.getThirtinthColumn());
		AllColumns.addAll(this.getFourtinthColumn());
		AllColumns.addAll(this.getFiftinthColumn());
		AllColumns.addAll(this.getSixtinthColumn());
		return AllColumns;
	}
	
	public ObservableList<Node> getFirstColumn(){
		return firstColumn;
	}
	
	public ObservableList<Node> getSecondColumn(){
		return secondColumn;
	}
	
	public ObservableList<Node> getThirdColumn(){
		return thirdColumn;
	}
	
	public ObservableList<Node> getFourthColumn(){
		return fourthColumn;
	}
	
	public ObservableList<Node> getFifthColumn(){
		return fifthColumn;
	}
	
	public ObservableList<Node> getSixthColumn(){
		return sixthColumn;
	}
	
	public ObservableList<Node> getSeventhColumn(){
		return seventhColumn;
	}
	
	public ObservableList<Node> getEighthColumn(){
		return eighthColumn;
	}
	
	public ObservableList<Node> getNinthColumn(){
		return ninthColumn;
	}
	
	public ObservableList<Node> getTenthColumn(){
		return tenthColumn;
	}
	
	public ObservableList<Node> getEleventhColumn(){
		return eleventhColumn;
	}
	
	public ObservableList<Node> getTwelthColumn(){
		return twelfthColumn;
	}
	
	public ObservableList<Node> getThirtinthColumn(){
		return thirtinthColumn;
	}
	
	public ObservableList<Node> getFourtinthColumn(){
		return fourteenthColumn;
	}
	
	public ObservableList<Node> getFiftinthColumn(){
		return fifteenthColumn;
	}
	
	public ObservableList<Node> getSixtinthColumn(){
		return sixteenthColumn;
	}

	
}
