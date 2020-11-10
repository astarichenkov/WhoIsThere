package ru.whoisthere.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.whoisthere.utils.Logging;
import ru.whoisthere.settings.DoorsReadersSettings;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static ru.whoisthere.utils.Logging.addInfoLog;

public class DoorsReadersOverviewController implements Initializable {
    //    private static Logging logs = new Logging();
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

    public void saveAndExit() {
        String role = "ADMIN";

        if (role.equals("ADMIN")) {
            File file = new File("doorsReaders.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
                writer.write(loginToTheHall.getText());
                writer.write(System.lineSeparator());
                writer.write(exitTheHall.getText());
                writer.write(System.lineSeparator());
                writer.write(logInToTheStore.getText());
                writer.write(System.lineSeparator());
                writer.write(exitOfTheStore.getText());
                addInfoLog("Settings was successfully recorded to file doorsReaders.txt");
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
//                DoorsReadersSettings.readFile();
            } catch (IOException e) {
                addInfoLog(e.getMessage() + " File reading error doorsReaders.txt");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginToTheHall.setText(String.valueOf(DoorsReadersSettings.getInputHall()));
        exitTheHall.setText(String.valueOf(DoorsReadersSettings.getOutputHall()));
        logInToTheStore.setText(String.valueOf(DoorsReadersSettings.getInputMag()));
        exitOfTheStore.setText(String.valueOf(DoorsReadersSettings.getExitMag()));
    }
}