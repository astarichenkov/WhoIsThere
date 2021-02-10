package ru.whoisthere.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.whoisthere.settings.DoorsReadersSettings;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import static ru.whoisthere.utils.Logging.addInfoLog;

public class DoorsReadersOverviewController implements Initializable {
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
        File file = new File("doorsReaders.properties");
        try {
            file.setExecutable(false);
            file.setReadable(false);
            file.setWritable(true);
        } catch (SecurityException e) {
            addInfoLog(e.getMessage() + "Exception in set file attributes");
        }
        Properties properties = new Properties();
        properties.put("inputHall", escapeHtml4(loginToTheHall.getText()));
        properties.put("outputHall", escapeHtml4(exitTheHall.getText()));
        properties.put("inputMag", escapeHtml4(logInToTheStore.getText()));
        properties.put("exitMag", escapeHtml4(exitOfTheStore.getText()));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            properties.store(fos, "comments");
        } catch (IOException e) {
            addInfoLog(e.getMessage() + "Exception in store doorsReaders.properties");
        }
        addInfoLog("Settings was successfully recorded to file doorsReaders.properties");
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginToTheHall.setText(String.valueOf(DoorsReadersSettings.getInputHall()));
        exitTheHall.setText(String.valueOf(DoorsReadersSettings.getOutputHall()));
        logInToTheStore.setText(String.valueOf(DoorsReadersSettings.getInputMag()));
        exitOfTheStore.setText(String.valueOf(DoorsReadersSettings.getExitMag()));
    }
}