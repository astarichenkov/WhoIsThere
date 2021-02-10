package ru.whoisthere;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.whoisthere.settings.ConnectionSettings;
import ru.whoisthere.settings.DoorsReadersSettings;

import java.io.IOException;

public class Main extends javafx.application.Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Кто в зале!?");
        ConnectionSettings.readFile();
        DoorsReadersSettings.readFile();
        Parent layout = FXMLLoader.load(getClass().getResource("/view/personsOverview.fxml"));
        Scene scene = new Scene(layout);

        ObservableList<Screen> screens = Screen.getScreens(); //Get list of Screens
        Rectangle2D bounds = screens.get(0).getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
