package ru.whoisthere.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PersonsData {
	
	private ArrayList<ArrayList<String>> personsDataAsArray;	
	private ObservableList<Node> AllColumns = FXCollections.observableArrayList();
	private VBox firstColumn = new VBox();
	private VBox secondColumn = new VBox();
	private VBox thirdColumn = new VBox();
	private VBox fourthColumn = new VBox();;
	private VBox fifthColumn = new VBox();
	private VBox sixthColumn = new VBox();
	private VBox seventhColumn = new VBox();
	private VBox eighthColumn = new VBox();
	private VBox ninthColumn = new VBox();
	private VBox tenthColumn = new VBox();
	private VBox eleventhColumn = new VBox();
	private VBox twelfthColumn = new VBox();
	private VBox thirtinthColumn = new VBox();
	private VBox fourteenthColumn = new VBox();
	private VBox fifteenthColumn = new VBox();
	private VBox sixteenthColumn = new VBox();
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
			for (int i = 0; i < personsDataAsArray.size(); i++) {
				VBox p = new VBox();
				p.setStyle("-fx-border-color:black");				
				p.getChildren().add(new Label(personsDataAsArray.get(i).get(0)));
				p.getChildren().add(new Label(personsDataAsArray.get(i).get(1)));	
				p.setAlignment(Pos.CENTER);
				String otdel = personsDataAsArray.get(i).get(5);
				
				switch (otdels.indexOf(otdel)){
				case 0:
					firstColumn.getChildren().add(p);
					break;
				case 1:
					secondColumn.getChildren().add(p);
					break;
				case 2:
					thirdColumn.getChildren().add(p);
					break;
				case 3:					
					fourthColumn.getChildren().add(p);
					break;
				case 4:
					fifthColumn.getChildren().add(p);
					break;
				case 5:					
					sixthColumn.getChildren().add(p);
					break;
				case 6:					
					seventhColumn.getChildren().add(p);
					break;
				case 7:					
					eighthColumn.getChildren().add(p);
					break;
				case 8:					
					ninthColumn.getChildren().add(p);
					break;
				case 9:					
					tenthColumn.getChildren().add(p);
					break;
				case 10:				
					eleventhColumn.getChildren().add(p);
					break;
				case 11:					
					twelfthColumn.getChildren().add(p);
					break;
				case 12:				
					thirtinthColumn.getChildren().add(p);
					break;
				case 13:					
					fourteenthColumn.getChildren().add(p);
					break;
				case 14:					
					fifteenthColumn.getChildren().add(p);
					break;
				case 15:					
					sixteenthColumn.getChildren().add(p);
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
	
	public VBox getFirstColumn(){
		return firstColumn;
	}
	
	public VBox getSecondColumn(){
		return secondColumn;
	}
	
	public VBox getThirdColumn(){
		return thirdColumn;
	}
	
	public VBox getFourthColumn(){
		return fourthColumn;
	}
	
	public VBox getFifthColumn(){
		return fifthColumn;
	}
	
	public VBox getSixthColumn(){
		return sixthColumn;
	}
	
	public VBox getSeventhColumn(){
		return seventhColumn;
	}
	
	public VBox getEighthColumn(){
		return eighthColumn;
	}
	
	public VBox getNinthColumn(){
		return ninthColumn;
	}
	
	public VBox getTenthColumn(){
		return tenthColumn;
	}
	
	public VBox getEleventhColumn(){
		return eleventhColumn;
	}
	
	public VBox getTwelthColumn(){
		return twelfthColumn;
	}
	
	public VBox getThirtinthColumn(){
		return thirtinthColumn;
	}
	
	public VBox getFourtinthColumn(){
		return fourteenthColumn;
	}
	
	public VBox getFiftinthColumn(){
		return fifteenthColumn;
	}
	
	public VBox getSixtinthColumn(){
		return sixteenthColumn;
	}

	
}
