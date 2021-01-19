package ru.whoisthere.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.whoisthere.utils.DownloadData;
import ru.whoisthere.utils.Logging;
import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
//        int maxPersons = 9;
//        int maxPersons = downloadData.getMaxPersons();
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
                    mynode.setHgap(5);
                    mynode.setVgap(5);

                    Image photo = SwingFXUtils.toFXImage(person.getPhoto(), null);

                    ImageView personPhoto = new ImageView(photo);
                    ColorAdjust grayScale = new ColorAdjust();
                    grayScale.setSaturation(-1);
                    if (!person.isPresent()) {
                        personPhoto.setEffect(grayScale);
                    }

                    double imgRatio = setImageRatio(photo);

                    personPhoto.fitWidthProperty().bind(
                            mynode.prefWidthProperty().multiply(imgRatio));
                    personPhoto.fitHeightProperty().bind(
                            mynode.prefHeightProperty().divide(9.2).subtract(40));
                    personPhoto.setPreserveRatio(true);


                    Label label = new Label(person.getName() + " " + System.lineSeparator()
                            + person.getSurname(), personPhoto);
//                    label.setWrapText(true);
                    label.setMaxWidth(55);
//                    label.setMinWidth(55);
                    label.setFont(Font.font("Roboto", 8.5));
//                    label.setStyle("-fx-border-color: black;");
                    label.setContentDisplay(ContentDisplay.TOP);
                    label.setAlignment(Pos.BOTTOM_RIGHT);
                    mynode.getChildren().addAll(label);

                }
            }
        }

        Date refreshingEnd = new Date();
        addInfoLog("Interface updated for: "
                + (refreshingEnd.getTime() - refreshingStart.getTime()) + " ms.");
        dataUpdated.setText(downloadData.getDataTime());
    }

    private double setImageRatio(Image photo) {
        double imgWidth = photo.getWidth();
        double imgHeight = photo.getHeight();
        return 1 * imgWidth / imgHeight;
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

        departLabel00.setText("СПОК");
        departLabel01.setText("1 отдел");
        departLabel02.setText("2 отдел");
        departLabel03.setText("3 отдел");
        departLabel04.setText("4 отдел");
        departLabel05.setText("5 отдел");
        departLabel06.setText("6 отдел");
        departLabel07.setText("7 отдел");
        departLabel08.setText("8 отдел");
        departLabel09.setText("9 отдел");
        departLabel10.setText("10 отдел");
        departLabel11.setText("11 отдел");
        departLabel12.setText("12 отдел");
        departLabel13.setText("13 отдел");
        departLabel14.setText("14 отдел");
        departLabel15.setText("15 отдел");

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
                loader.setLocation(getClass().getResource("/ru/whoisthere/view/connectionOverview.fxml"));
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
        menuItem2.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/ru/whoisthere/view/doorsReadersOverview.fxml"));
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
        menuItem3.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/ru/whoisthere/view/departsOverview.fxml"));
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