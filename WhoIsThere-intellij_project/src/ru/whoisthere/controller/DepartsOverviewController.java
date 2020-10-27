package ru.whoisthere.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.whoisthere.utils.Loging;
import ru.whoisthere.model.Departments;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.ResourceBundle;

public class DepartsOverviewController implements Initializable {
    private static Loging logs = new Loging();

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

    public void saveAndExit() {
        String host = "";
        String role = "";
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            logs.addInfoLog(e.getMessage());
        }
        if (host.contains("leroymerlin")) {
            role = "ADMIN";
        }

        if (role.equals("ADMIN")) {
            String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            try {
                userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            } catch (SecurityException e) {
                logs.addInfoLog(e.getMessage());
            }
            File file = new File(userDir, "departs.txt");
            file.setExecutable(false);
            file.setReadable(true);
            file.setWritable(true);

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    file), StandardCharsets.UTF_8))) {
                writer.write(departKey00.getText() + ", " + departLabel00.getText());
                writer.newLine();
                writer.write(departKey01.getText() + ", " + departLabel01.getText());
                writer.newLine();
                writer.write(departKey02.getText() + ", " + departLabel02.getText());
                writer.newLine();
                writer.write(departKey03.getText() + ", " + departLabel03.getText());
                writer.newLine();
                writer.write(departKey04.getText() + ", " + departLabel04.getText());
                writer.newLine();
                writer.write(departKey05.getText() + ", " + departLabel05.getText());
                writer.newLine();
                writer.write(departKey06.getText() + ", " + departLabel06.getText());
                writer.newLine();
                writer.write(departKey07.getText() + ", " + departLabel07.getText());
                writer.newLine();
                writer.write(departKey08.getText() + ", " + departLabel08.getText());
                writer.newLine();
                writer.write(departKey09.getText() + ", " + departLabel09.getText());
                writer.newLine();
                writer.write(departKey10.getText() + ", " + departLabel10.getText());
                writer.newLine();
                writer.write(departKey11.getText() + ", " + departLabel11.getText());
                writer.newLine();
                writer.write(departKey12.getText() + ", " + departLabel12.getText());
                writer.newLine();
                writer.write(departKey13.getText() + ", " + departLabel13.getText());
                writer.newLine();
                writer.write(departKey14.getText() + ", " + departLabel14.getText());
                writer.newLine();
                writer.write(departKey15.getText() + ", " + departLabel15.getText());
                logs.addInfoLog("Settings was successfully recorded to file departs.txt");
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                logs.addInfoLog(e.getMessage() + " File reading error departs.txt");
            }
        }
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