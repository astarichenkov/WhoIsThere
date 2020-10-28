package ru.whoisthere.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
        String host = "";
        String role = "";
        System.out.println(1);
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            logs.addInfoLog(e.getMessage());
        }
        if (host.contains("leroymerlin")) {
            role = "ADMIN";
        }
        System.out.println(2);
        if (role.equals("ADMIN")) {
            String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            try {
                userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            } catch (SecurityException e) {
                logs.addInfoLog(e.getMessage());
            }

            File file = new File("connection.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    file), StandardCharsets.UTF_8))) {
                writer.write(loginField.getText());
                writer.newLine();
                String encodedAsswd = Base64.getEncoder().encodeToString(
                        passwordField.getText().getBytes());
                writer.write(encodedAsswd);
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