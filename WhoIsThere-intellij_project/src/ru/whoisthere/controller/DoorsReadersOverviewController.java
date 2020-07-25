package ru.whoisthere.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.whoisthere.utils.Loging;
import ru.whoisthere.settings.DoorsReadersSettings;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class DoorsReadersOverviewController implements Initializable {
    private static Loging logs = new Loging();

    @FXML
    private Button cancelButton;

    @FXML
    private TextField logInToTheStore;

    @FXML
    private TextField exitOfTheStore;

    @FXML
    private Button okButton;

    @FXML
    private TextField exitTheHall;

    @FXML
    private TextField loginToTheHall;

    public void closeWindow() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void saveAndExit() throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                "C:\\WhoIsThere\\doorsReaders.txt"), StandardCharsets.UTF_8))) {
            writer.write(loginToTheHall.getText());
            writer.newLine();
            writer.write(exitTheHall.getText());
            writer.newLine();
            writer.write(logInToTheStore.getText());
            writer.newLine();
            writer.write(exitOfTheStore.getText());
            logs.addInfoLog("Settings was successfully recorded to file doorsReaders.txt");
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            logs.addInfoLog("File reading error doorsReaders.txt");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DoorsReadersSettings doorsReadersSettings = new DoorsReadersSettings();
        loginToTheHall.setText(String.valueOf(doorsReadersSettings.getInputHall()));
        exitTheHall.setText(String.valueOf(doorsReadersSettings.getOutputHall()));
        logInToTheStore.setText(String.valueOf(doorsReadersSettings.getInputMag()));
        exitOfTheStore.setText(String.valueOf(doorsReadersSettings.getExitMag()));
    }
}