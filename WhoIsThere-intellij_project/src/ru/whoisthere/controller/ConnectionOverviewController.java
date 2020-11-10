package ru.whoisthere.controller;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.whoisthere.settings.ConnectionSettings;

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
        String role = "ADMIN";
        if (role.equals("ADMIN")) {
            File file = new File("connection.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
                fileWriter.write(loginField.getText());
                fileWriter.write(System.lineSeparator());
                String encodedAsswd = Base64.getEncoder().encodeToString(
                        passwordField.getText().getBytes());
                fileWriter.write(encodedAsswd);
                fileWriter.write(System.lineSeparator());
                fileWriter.write(ipAddressField.getText());
                fileWriter.write(System.lineSeparator());
                fileWriter.write(pathToDBField.getText());
                fileWriter.flush();

                addInfoLog("Settings was successfully recorded to file connection.txt");
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
//                ConnectionSettings.readFile();
            } catch (IOException e) {
                addInfoLog(e.getMessage() + " File reading error connection.txt");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginField.setText(ConnectionSettings.getLogin());
        passwordField.setText(ConnectionSettings.getAsswd());
        pathToDBField.setText(ConnectionSettings.getPathToDB());
        ipAddressField.setText(ConnectionSettings.getIp());
    }
}