package ru.whoisthere.view;

import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ru.whoisthere.DownloadData;
import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

public class PersonsOverviewController {
	DownloadData downloadData = new DownloadData();
	Departments departs = new Departments();	
	
	@FXML 
	private GridPane gp = new GridPane();
	@FXML
	private Label dataUpdated = new Label();

	public void refreshScreen() {
		try {
			Date refreshingStart = new Date();
			System.out.println("Интерфейс. Начало обновления интерфейса: " + refreshingStart);
			clearData();
			int maxPersons = downloadData.getMaxPersons();
			for (int i = 0; i<departs.getOtdelsCount(); i++) {
				for (Person person : downloadData.getPersons()) {			
					if (person.getDepartment().equals(departs.getDepartmentName(i))) {
						String nodeName = "col" + i;
						VBox mynode = (VBox) gp.lookup("#" + nodeName);
						mynode.setMinHeight(gp.getRowConstraints().get(1).getMinHeight());
						//------------ элемент с фотографией--------------------------------
						Image photo = SwingFXUtils.toFXImage(person.getPhoto(), null);
						ImageView personPhoto = new ImageView(photo);	
						Double imgW = photo.getWidth();
						Double imgH = photo.getHeight();
						Double ratio = imgH/imgW;
						Double containerHeight = mynode.getHeight();
						Double containerWidth = mynode.getWidth()-10;					
						Double calcHeight = containerWidth * ratio;
						Double defHeight = (containerWidth) * 1.3;
						Double calcWidth = defHeight / ratio;
						
						if (calcHeight + 40 > containerHeight/maxPersons) {
							personPhoto.setFitHeight(containerHeight/maxPersons - 40);						
						} else {
							if (calcWidth > containerWidth)
								personPhoto.setFitWidth(containerWidth);
							else personPhoto.setFitHeight(defHeight);
						}
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
						personInfoContainer.getChildren().addAll(personPhoto, personName);					
						//=========================================================================
						
						mynode.getChildren().add(personInfoContainer);				
					}
				}
			}
			Date refreshingEnd = new Date();
			System.out.println("Интерфейс. Обновлен за: " + (refreshingEnd.getTime() - refreshingStart.getTime())/1000 + " сек.");
			dataUpdated.setText(downloadData.getDataTime());
		} catch (Exception e) {
			e.printStackTrace();
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
	
	//@FXML
	public void initialize() {
		downloadData.start();
		ContextMenu cm = new ContextMenu();
		MenuItem menuItem0 = new MenuItem("Полноэкранный режим");
		MenuItem menuItem1 = new MenuItem("Выйти из приложения");
		//-----------------обработка событий нажатия пунктов меню---------------------
		menuItem0.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				//stage.setFullScreen(true);
			}
		});		
		menuItem1.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);			
			}
		});
		//===========================================================================
		cm.getItems().addAll(menuItem0, new SeparatorMenuItem(), menuItem1);
		
		gp.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				cm.show(gp, event.getScreenX(), event.getScreenY());
			}
		});
		
		gp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton()==MouseButton.PRIMARY) {					
					cm.hide();
				}				
			}
		});
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15.0), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refreshScreen();
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
	}	
}
