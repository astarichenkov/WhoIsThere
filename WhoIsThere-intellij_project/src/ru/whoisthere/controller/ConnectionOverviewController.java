package ru.whoisthere.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
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
import ru.whoisthere.utils.SanitizePath;

public class ConnectionOverviewController implements Initializable {
    private static Loging logs = new Loging();

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
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        File file = new File(userDir, "connection.txt");
//        String filename = SanitizePath.sanitizePathTraversal("connection.txt");
//        File file = new File(filename);

        String host = "";
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            logs.addInfoLog("error in getLocalHost");
        }
        if (host.contains("leroymerlin")) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    file), StandardCharsets.UTF_8))) {
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
                logs.addInfoLog(e.getMessage() + " File reading error connection.txt");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionSettings connectionSettings = new ConnectionSettings();
        loginField.setText(connectionSettings.getLogin());
        passwordField.setText(connectionSettings.getAsswd());
        pathToDBField.setText(connectionSettings.getPathToDB());
        ipAddressField.setText(connectionSettings.getIp());
    }
}