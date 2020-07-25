package ru.whoisthere.controller;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.whoisthere.utils.Loging;
import ru.whoisthere.settings.ConnectionSettings;

public class ConnectionOverviewController implements Initializable {
    private static Loging logs = new Loging();

    @FXML
    private Button cancelButton;

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

    public void saveAndExit() throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                "C:\\WhoIsThere\\connection.txt"), StandardCharsets.UTF_8))) {
            writer.write(loginField.getText());
            writer.newLine();
            writer.write(passwordField.getText());
            writer.newLine();
            writer.write(ipAddressField.getText());
            writer.newLine();
            writer.write(pathToDBField.getText());
            logs.addInfoLog("Settings was successfully recorded to file connection.txt");
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            logs.addInfoLog("File reading error connection.txt");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionSettings connectionSettings = new ConnectionSettings();
        loginField.setText(connectionSettings.getLogin());
        passwordField.setText(connectionSettings.getPassword());
        pathToDBField.setText(connectionSettings.getPathToDB());
        ipAddressField.setText(connectionSettings.getIp());

    }
}