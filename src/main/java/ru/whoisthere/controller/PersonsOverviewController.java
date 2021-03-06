package ru.whoisthere.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.whoisthere.utils.DownloadData;
import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import static ru.whoisthere.utils.Logging.addInfoLog;

public class PersonsOverviewController {
    private Departments departs = new Departments();
    private List<Person> persons = Collections.synchronizedList(new ArrayList<>());

    @FXML
    private GridPane gp = new GridPane();
    @FXML
    private Label dataUpdated = new Label();
    @FXML
    private Label departLabel00;
    @FXML
    private Label departLabel01;
    @FXML
    private Label departLabel02;
    @FXML
    private Label departLabel03;
    @FXML
    private Label departLabel04;
    @FXML
    private Label departLabel05;
    @FXML
    private Label departLabel06;
    @FXML
    private Label departLabel07;
    @FXML
    private Label departLabel08;
    @FXML
    private Label departLabel09;
    @FXML
    private Label departLabel10;
    @FXML
    private Label departLabel11;
    @FXML
    private Label departLabel12;
    @FXML
    private Label departLabel13;
    @FXML
    private Label departLabel14;
    @FXML
    private Label departLabel15;

    public void refreshScreen() {
        clearData();
        DownloadData downloadData = new DownloadData();
        persons = downloadData.getPersons();
        Date refreshingStart = new Date();
        Stage stage = (Stage) gp.getScene().getWindow();
        gp.prefHeightProperty().bind(stage.heightProperty().subtract(40));

        for (int i = 0; i < 16; i++) {
            FlowPane column = (FlowPane) gp.lookup("#col" + i);
            column.prefHeightProperty().bind(gp.prefHeightProperty().subtract(40));
            column.prefWidthProperty().bind(
                    gp.getColumnConstraints().get(0).prefWidthProperty());
            column.setAlignment(Pos.TOP_CENTER);
        }

        for (Person person : persons) {
            for (int i = 0; i < 16; i++) {
                if (person.getDepartment().equals(departs.getDepartmentName(i))) {
                    FlowPane mynode = (FlowPane) gp.lookup("#col" + i);
                    mynode.setHgap(1);
                    mynode.setVgap(4);

                    Image photo = SwingFXUtils.toFXImage(person.getPhoto(), null);

                    ImageView personPhoto = new ImageView(photo);
                    ColorAdjust grayScale = new ColorAdjust();
                    grayScale.setSaturation(-1);
                    if (!person.isPresent()) {
                        personPhoto.setEffect(grayScale);
                    }

                    Label label = new Label(person.getName() + " " + System.lineSeparator()
                            + person.getSurname(), personPhoto);

                    label.setPrefWidth(58);
                    label.setMinWidth(58);
                    label.setMaxWidth(58);

                    label.setFont(Font.font("Roboto", 8.5));
                    label.setStyle("-fx-border-color: gray; "
                            + "-fx-border-radius: 1; "
                            + "-fx-background-radius: 1;");
                    label.setContentDisplay(ContentDisplay.TOP);
                    label.setAlignment(Pos.CENTER);
                    label.setTextAlignment(TextAlignment.CENTER);

                    mynode.getChildren().addAll(label);
                }
            }
        }

        Date refreshingEnd = new Date();
        addInfoLog("Interface updated for: "
                + (refreshingEnd.getTime() - refreshingStart.getTime()) + " ms.");
        dataUpdated.setText(downloadData.getDataTime());
    }

    private void clearData() {
        for (Node n : gp.getChildren()) {
            if (n instanceof Pane) {
                for (Node n1 : ((Pane) n).getChildren()) {
                    if (n1.getStyleClass().toString().equals("columns")) {
                        FlowPane node = (FlowPane) n1;
                        persons = null;
                        node.getChildren().clear();
                    }
                }
            }
        }
    }

    @FXML
    public void initialize() {

        departLabel00.setText(escapeHtml4(departs.getColumnLables().get(0)));
        departLabel01.setText(escapeHtml4(departs.getColumnLables().get(1)));
        departLabel02.setText(escapeHtml4(departs.getColumnLables().get(2)));
        departLabel03.setText(escapeHtml4(departs.getColumnLables().get(3)));
        departLabel04.setText(escapeHtml4(departs.getColumnLables().get(4)));
        departLabel05.setText(escapeHtml4(departs.getColumnLables().get(5)));
        departLabel06.setText(escapeHtml4(departs.getColumnLables().get(6)));
        departLabel07.setText(escapeHtml4(departs.getColumnLables().get(7)));
        departLabel08.setText(escapeHtml4(departs.getColumnLables().get(8)));
        departLabel09.setText(escapeHtml4(departs.getColumnLables().get(9)));
        departLabel10.setText(escapeHtml4(departs.getColumnLables().get(10)));
        departLabel11.setText(escapeHtml4(departs.getColumnLables().get(11)));
        departLabel12.setText(escapeHtml4(departs.getColumnLables().get(12)));
        departLabel13.setText(escapeHtml4(departs.getColumnLables().get(13)));
        departLabel14.setText(escapeHtml4(departs.getColumnLables().get(14)));
        departLabel15.setText(escapeHtml4(departs.getColumnLables().get(15)));

        ContextMenu cm = new ContextMenu();
        MenuItem menuItem0 = new MenuItem();
        MenuItem menuItem1 = new MenuItem("Настройки соединения с БД");
        MenuItem menuItem2 = new MenuItem("Настройки считывателей");
        MenuItem menuItem3 = new MenuItem("Список отделов");
        //-----------------обработка событий нажатия пунктов меню---------------------
        menuItem0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) gp.getScene().getWindow();
                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    menuItem0.setText("Полноэкранный режим");
                } else {
                    stage.setFullScreen(true);
                    menuItem0.setText("Оконный режим");
                }

            }
        });
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/connectionOverview.fxml"));
                try {
                    loader.load();
                } catch (NullPointerException | IOException e) {
                    addInfoLog(e.getMessage() + "Error in getResource connectionOverview.fxml");
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setTitle("Настройки соединения с БД");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
        menuItem2.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/doorsReadersOverview.fxml"));
                try {
                    loader.load();
                } catch (NullPointerException | IOException e) {
                    addInfoLog(e.getMessage() + "Error in getResource doorsReadersOverview.fxml");
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setTitle("Настройки считывателей");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
        menuItem3.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/departsOverview.fxml"));
                try {
                    loader.load();
                } catch (NullPointerException | IOException e) {
                    addInfoLog(e.getMessage() + "Error in getResource departsOverview.fxml");
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setTitle("Список отделов");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
        //===========================================================================
        cm.getItems().addAll(menuItem0,
                new SeparatorMenuItem(), menuItem1,
                new SeparatorMenuItem(), menuItem2,
                new SeparatorMenuItem(), menuItem3);

        gp.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                Stage stage = (Stage) gp.getScene().getWindow();
                if (stage.isFullScreen()) {
                    menuItem0.setText("Оконный режим");

                } else {
                    menuItem0.setText("Полноэкранный режим");
                }
                cm.show(gp, event.getScreenX(), event.getScreenY());
            }
        });

        gp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    cm.hide();
                }
            }
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        refreshScreen();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}