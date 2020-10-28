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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.whoisthere.utils.DownloadData;
import ru.whoisthere.utils.Loging;
import ru.whoisthere.model.Departments;
import ru.whoisthere.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonsOverviewController {
    private static Loging logs = new Loging();
    private Departments departs = new Departments();
    private DownloadData downloadData = new DownloadData();
    private List<Person> persons = new ArrayList<>();

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
        persons = downloadData.getPersons();
        Date refreshingStart = new Date();
        logs.addInfoLog("Start updating the interface: " + refreshingStart);
        int maxPersons = downloadData.getMaxPersons();

        Stage stage = (Stage) gp.getScene().getWindow();
        gp.prefHeightProperty().bind(stage.heightProperty().subtract(40));

        for (int i = 0; i < 16; i++) {
            VBox column = (VBox) gp.lookup("#col" + i);
            column.prefHeightProperty().bind(gp.prefHeightProperty().subtract(40));
            column.prefWidthProperty().bind(
                    gp.getColumnConstraints().get(0).prefWidthProperty());
            column.setAlignment(Pos.TOP_CENTER);
        }

        for (Person person : persons) {
            for (int i = 0; i < 16; i++) {
                if (person.getDepartment().equals(departs.getDepartmentName(i))) {
                    VBox mynode = (VBox) gp.lookup("#col" + i);

                    Image photo = SwingFXUtils.toFXImage(person.getPhoto(), null);
                    ImageView personPhoto = new ImageView(photo);
                    double imgRatio = setImageRatio(photo);

                    personPhoto.fitWidthProperty().bind(
                            mynode.prefWidthProperty().multiply(imgRatio));
                    personPhoto.fitHeightProperty().bind(
                            mynode.prefHeightProperty().divide(maxPersons).subtract(40));
                    personPhoto.setPreserveRatio(true);

                    mynode.getChildren().addAll(
                            personPhoto, new Label(person.getName()), new Label(
                                    person.getSurname()));
                }
            }
        }

        Date refreshingEnd = new Date();
        logs.addInfoLog("Interface updated for: "
                + (refreshingEnd.getTime() - refreshingStart.getTime()) / 1000 + " seconds.");
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
                        VBox node = (VBox) n1;
                        persons = null;
                        node.getChildren().clear();
                    }
                }
            }
        }
    }

    @FXML
    public void initialize() {
        departLabel00.setText(departs.getDepartments().get(0).get(1));
        departLabel01.setText(departs.getDepartments().get(1).get(1));
        departLabel02.setText(departs.getDepartments().get(2).get(1));
        departLabel03.setText(departs.getDepartments().get(3).get(1));
        departLabel04.setText(departs.getDepartments().get(4).get(1));
        departLabel05.setText(departs.getDepartments().get(5).get(1));
        departLabel06.setText(departs.getDepartments().get(6).get(1));
        departLabel07.setText(departs.getDepartments().get(7).get(1));
        departLabel08.setText(departs.getDepartments().get(8).get(1));
        departLabel09.setText(departs.getDepartments().get(9).get(1));
        departLabel10.setText(departs.getDepartments().get(10).get(1));
        departLabel11.setText(departs.getDepartments().get(11).get(1));
        departLabel12.setText(departs.getDepartments().get(12).get(1));
        departLabel13.setText(departs.getDepartments().get(13).get(1));
        departLabel14.setText(departs.getDepartments().get(14).get(1));
        departLabel15.setText(departs.getDepartments().get(15).get(1));

        downloadData.start();

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
                } catch (IOException e) {
                    logs.addInfoLog(e.getMessage());
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
                } catch (IOException e) {
                    logs.addInfoLog(e.getMessage());
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
                } catch (IOException e) {
                    logs.addInfoLog(e.getMessage());
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
                new KeyFrame(Duration.seconds(30.0), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        refreshScreen();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}