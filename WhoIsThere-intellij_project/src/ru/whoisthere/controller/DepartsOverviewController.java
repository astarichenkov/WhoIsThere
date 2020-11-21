package ru.whoisthere.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.whoisthere.model.Departments;
import ru.whoisthere.settings.ConnectionSettings;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;
import java.util.ResourceBundle;

import static ru.whoisthere.utils.Logging.addInfoLog;
import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;


public class DepartsOverviewController implements Initializable {

    @FXML
    private Button okButton;
    @FXML
    private TextField departLabel00;
    @FXML
    private TextField departLabel01;
    @FXML
    private TextField departLabel02;
    @FXML
    private TextField departLabel03;
    @FXML
    private TextField departLabel04;
    @FXML
    private TextField departLabel05;
    @FXML
    private TextField departLabel06;
    @FXML
    private TextField departLabel07;
    @FXML
    private TextField departLabel08;
    @FXML
    private TextField departLabel09;
    @FXML
    private TextField departLabel10;
    @FXML
    private TextField departLabel11;
    @FXML
    private TextField departLabel12;
    @FXML
    private TextField departLabel13;
    @FXML
    private TextField departLabel14;
    @FXML
    private TextField departLabel15;

    @FXML
    private TextField departKey00;
    @FXML
    private TextField departKey01;
    @FXML
    private TextField departKey02;
    @FXML
    private TextField departKey03;
    @FXML
    private TextField departKey04;
    @FXML
    private TextField departKey05;
    @FXML
    private TextField departKey06;
    @FXML
    private TextField departKey07;
    @FXML
    private TextField departKey08;
    @FXML
    private TextField departKey09;
    @FXML
    private TextField departKey10;
    @FXML
    private TextField departKey11;
    @FXML
    private TextField departKey12;
    @FXML
    private TextField departKey13;
    @FXML
    private TextField departKey14;
    @FXML
    private TextField departKey15;

    public void closeWindow() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }


    public void writeToFile(Properties properties) {
        File file = new File("departs.properties");
        try {
            file.setExecutable(false);
            file.setReadable(false);
            file.setWritable(true);
        } catch (SecurityException e) {
            addInfoLog(e.getMessage() + "Exception in set file attributes");
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            properties.store(fos, "comments");
        } catch (IOException e) {
            addInfoLog(e.getMessage() + "Exception in store properties");
        }
    }

    public void saveAndExit() {
            File file = new File("departs.properties");
            try {
                file.setExecutable(false);
                file.setReadable(true);
                file.setWritable(true);
            } catch (SecurityException e) {
                addInfoLog(e.getMessage() + "Exception in set file attributes");
            }
            Properties properties = new Properties();
            properties.put("departKey00", escapeHtml4(departLabel00.getText()));
            properties.put("departKey01", escapeHtml4(departLabel01.getText()));
            properties.put("departKey02", escapeHtml4(departLabel02.getText()));
            properties.put("departKey03", escapeHtml4(departLabel03.getText()));
            properties.put("departKey04", escapeHtml4(departLabel04.getText()));
            properties.put("departKey05", escapeHtml4(departLabel05.getText()));
            properties.put("departKey06", escapeHtml4(departLabel06.getText()));
            properties.put("departKey07", escapeHtml4(departLabel07.getText()));
            properties.put("departKey08", escapeHtml4(departLabel08.getText()));
            properties.put("departKey09", escapeHtml4(departLabel09.getText()));
            properties.put("departKey10", escapeHtml4(departLabel10.getText()));
            properties.put("departKey11", escapeHtml4(departLabel11.getText()));
            properties.put("departKey12", escapeHtml4(departLabel12.getText()));
            properties.put("departKey13", escapeHtml4(departLabel13.getText()));
            properties.put("departKey14", escapeHtml4(departLabel14.getText()));
            properties.put("departKey15", escapeHtml4(departLabel15.getText()));
            writeToFile(properties);

            addInfoLog("Settings was successfully recorded to file departs.properties");
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Departments departments = new Departments();
        departLabel00.setText(departments.getDepartments().get(0).get(1));
        departLabel01.setText(departments.getDepartments().get(1).get(1));
        departLabel02.setText(departments.getDepartments().get(2).get(1));
        departLabel03.setText(departments.getDepartments().get(3).get(1));
        departLabel04.setText(departments.getDepartments().get(4).get(1));
        departLabel05.setText(departments.getDepartments().get(5).get(1));
        departLabel06.setText(departments.getDepartments().get(6).get(1));
        departLabel07.setText(departments.getDepartments().get(7).get(1));
        departLabel08.setText(departments.getDepartments().get(8).get(1));
        departLabel09.setText(departments.getDepartments().get(9).get(1));
        departLabel10.setText(departments.getDepartments().get(10).get(1));
        departLabel11.setText(departments.getDepartments().get(11).get(1));
        departLabel12.setText(departments.getDepartments().get(12).get(1));
        departLabel13.setText(departments.getDepartments().get(13).get(1));
        departLabel14.setText(departments.getDepartments().get(14).get(1));
        departLabel15.setText(departments.getDepartments().get(15).get(1));

        departKey00.setText(departments.getDepartments().get(0).get(0));
        departKey01.setText(departments.getDepartments().get(1).get(0));
        departKey02.setText(departments.getDepartments().get(2).get(0));
        departKey03.setText(departments.getDepartments().get(3).get(0));
        departKey04.setText(departments.getDepartments().get(4).get(0));
        departKey05.setText(departments.getDepartments().get(5).get(0));
        departKey06.setText(departments.getDepartments().get(6).get(0));
        departKey07.setText(departments.getDepartments().get(7).get(0));
        departKey08.setText(departments.getDepartments().get(8).get(0));
        departKey09.setText(departments.getDepartments().get(9).get(0));
        departKey10.setText(departments.getDepartments().get(10).get(0));
        departKey11.setText(departments.getDepartments().get(11).get(0));
        departKey12.setText(departments.getDepartments().get(12).get(0));
        departKey13.setText(departments.getDepartments().get(13).get(0));
        departKey14.setText(departments.getDepartments().get(14).get(0));
        departKey15.setText(departments.getDepartments().get(15).get(0));
    }
}