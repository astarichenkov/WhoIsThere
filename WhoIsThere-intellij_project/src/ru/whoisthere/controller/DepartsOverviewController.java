package ru.whoisthere.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.whoisthere.model.Departments;

import java.io.*;
import java.net.URL;
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
        properties.put("depart0", escapeHtml4(departKey00.getText()));
        properties.put("depart1", escapeHtml4(departKey01.getText()));
        properties.put("depart2", escapeHtml4(departKey02.getText()));
        properties.put("depart3", escapeHtml4(departKey03.getText()));
        properties.put("depart4", escapeHtml4(departKey04.getText()));
        properties.put("depart5", escapeHtml4(departKey05.getText()));
        properties.put("depart6", escapeHtml4(departKey06.getText()));
        properties.put("depart7", escapeHtml4(departKey07.getText()));
        properties.put("depart8", escapeHtml4(departKey08.getText()));
        properties.put("depart9", escapeHtml4(departKey09.getText()));
        properties.put("depart10", escapeHtml4(departKey10.getText()));
        properties.put("depart11", escapeHtml4(departKey11.getText()));
        properties.put("depart12", escapeHtml4(departKey12.getText()));
        properties.put("depart13", escapeHtml4(departKey13.getText()));
        properties.put("depart14", escapeHtml4(departKey14.getText()));
        properties.put("depart15", escapeHtml4(departKey15.getText()));
        writeToFile(properties);

        addInfoLog("Settings was successfully recorded to file departs.properties");
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Departments departments = new Departments();
        departLabel00.setText("ЧПОК");
        departLabel01.setText("1 отдел");
        departLabel02.setText("2 отдел");
        departLabel03.setText("3 отдел");
        departLabel04.setText("4 отдел");
        departLabel05.setText("5 отдел");
        departLabel06.setText("6 отдел");
        departLabel07.setText("7 отдел");
        departLabel08.setText("8 отдел");
        departLabel09.setText("9 отдел");
        departLabel10.setText("10 отдел");
        departLabel11.setText("11 отдел");
        departLabel12.setText("12 отдел");
        departLabel13.setText("13 отдел");
        departLabel14.setText("14 отдел");
        departLabel15.setText("15 отдел");

        departKey00.setText(departments.getDepartments().get(0));
        departKey01.setText(departments.getDepartments().get(1));
        departKey02.setText(departments.getDepartments().get(2));
        departKey03.setText(departments.getDepartments().get(3));
        departKey04.setText(departments.getDepartments().get(4));
        departKey05.setText(departments.getDepartments().get(5));
        departKey06.setText(departments.getDepartments().get(6));
        departKey07.setText(departments.getDepartments().get(7));
        departKey08.setText(departments.getDepartments().get(8));
        departKey09.setText(departments.getDepartments().get(9));
        departKey10.setText(departments.getDepartments().get(10));
        departKey11.setText(departments.getDepartments().get(11));
        departKey12.setText(departments.getDepartments().get(12));
        departKey13.setText(departments.getDepartments().get(13));
        departKey14.setText(departments.getDepartments().get(14));
        departKey15.setText(departments.getDepartments().get(15));
    }
}