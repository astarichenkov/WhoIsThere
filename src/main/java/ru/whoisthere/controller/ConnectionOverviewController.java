package ru.whoisthere.controller;

import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.whoisthere.settings.ConnectionSettings;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import static ru.whoisthere.utils.Logging.addInfoLog;

public class ConnectionOverviewController implements Initializable {

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button okButton;

    @FXML
    private TextField ipAddressField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField pathToDBField;

    public void closeWindow() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void saveAndExit() {
            File file = new File("connection.properties");
            try {
                file.setExecutable(false);
                file.setReadable(true);
                file.setWritable(true);
            } catch (SecurityException e) {
                addInfoLog(e.getMessage() + "Exception in set file attributes");
            }
            Properties properties = new Properties();
            properties.put("user", escapeHtml4(loginField.getText()));
            String encodedAsswd = Base64.getEncoder().encodeToString(
                    escapeHtml4(passwordField.getText()).getBytes());
            properties.put("asswd", encodedAsswd);
            properties.put("ip", escapeHtml4(ipAddressField.getText()));
            properties.put("pathToDB", escapeHtml4(pathToDBField.getText()));
            properties.put("encoding", "UTF8");
            properties.put("url", "jdbc:firebirdsql://");
            ConnectionSettings.writeToFile(properties);

            addInfoLog("Settings was successfully recorded to file connection.properties");
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginField.setText(ConnectionSettings.getLogin());
        passwordField.setText(ConnectionSettings.getAsswd());
        pathToDBField.setText(ConnectionSettings.getPathToDB());
        ipAddressField.setText(ConnectionSettings.getIp());
    }
}