package com.example.lab5.Controllers.TaskTable;

import com.example.lab5.Classes.TaskTable;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskTableEditController implements Initializable {
    public AnchorPane topPane;
    public ComboBox<Integer> cmbId = new ComboBox<>();
    public Button btnDelete;
    public TextArea txtInfo;
    public AnchorPane bottomPane;
    public ComboBox<Integer> cmbStaff = new ComboBox<>();
    public ComboBox<Integer> cmbHall = new ComboBox<>();
    public ComboBox<Integer> cmbClient = new ComboBox<>();
    public DatePicker dpDate;
    public TextArea txtStaffInfo;
    public TextArea txtHallInfo;
    public TextArea txtClientInfo;
    public Button btnAdd;
    public Label errorLog;
    public ComboBox<Integer> cmbDuration = new ComboBox<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        var ids = DataBase.getClientIds();
        cmbClient.setItems(ids);
        if(ids.size()>=1) {
            cmbClient.setValue(ids.get(0));
            txtClientInfo.setText(DataBase.foundClient(cmbClient.getValue()).toString());
        }
        else{
            txtClientInfo.setText("Нет клиентов");
            cmbClient.setDisable(true);
        }
        var ids1 = DataBase.getStaffIds();
        cmbStaff.setItems(ids1);
        if(ids1.size()>=1) {
            cmbStaff.setValue(ids1.get(0));
            txtStaffInfo.setText(DataBase.foundStaff(cmbStaff.getValue()).toString());
        }
        else{
            txtStaffInfo.setText("Нет персонала");
            cmbStaff.setDisable(true);
        }
        var ids2 = DataBase.getHallId();
        cmbHall.setItems(ids2);
        if(ids2.size()>=1) {
            cmbHall.setValue(ids1.get(0));
            txtHallInfo.setText(DataBase.foundHall(cmbHall.getValue()).toString());
        }
        else{
            txtHallInfo.setText("Нет залов");
            cmbHall.setDisable(true);
        }
        ObservableList<Integer> duration = FXCollections.observableArrayList();
        for (int i = 30; i <= 120; i +=15){
            duration.add(i);
        }
        cmbDuration.setItems(duration);
        cmbDuration.setValue(duration.get(0));
        ids = DataBase.getTaskTableId();
        cmbId.setItems(ids);
        if(ids.size()>=1) {
            cmbId.setValue(ids.get(0));
            txtInfo.setText(DataBase.foundTaskTable(cmbId.getValue()).toString());
        }
        else{
            txtInfo.setText("Все занятия удалены");
            cmbId.setDisable(true);
        }
    }

    public void cmbIdSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundTaskTable(cmbId.getValue());
        txtInfo.setText(info.toString());
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        var tt = DataBase.foundTaskTable(cmbId.getValue());
        cmbClient.setValue(tt.getCodeClient());
        cmbDuration.setValue(tt.getDuration());
        cmbHall.setValue(tt.getCodeHall());
        cmbStaff.setValue(tt.getCodeStaff());
        dpDate.setValue((tt.getDate().toLocalDate()));
        topPane.setDisable(true);
        bottomPane.setVisible(true);
    }

    public void cmbStaffSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundStaff(cmbStaff.getValue());
        txtStaffInfo.setText(info.toString());
    }

    public void cmbHallSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundHall(cmbHall.getValue());
        txtHallInfo.setText(info.toString());
    }

    public void cmbClientSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundClient(cmbClient.getValue());
        txtClientInfo.setText(info.toString());
    }

    public void btnAddClick(ActionEvent actionEvent) {
        var tt = new TaskTable();
        if(dpDate.getValue() == null){
            errorLog.setText("Вы не ввели дату");
            return;
        }
        var nDay = LocalDate.now().getDayOfMonth();
        var nMonth = LocalDate.now().getMonth().getValue();
        var nYear = LocalDate.now().getYear();
        var uDay = dpDate.getValue().getDayOfMonth();
        var uMonth = dpDate.getValue().getMonth().getValue();
        var uYear = dpDate.getValue().getYear();
        if(nYear>=uYear && nMonth>=uMonth && nDay>uDay){
            errorLog.setText("Дата занятия меньше сегодняшней даты");
            return;
        }
        tt.setDuration(cmbDuration.getValue());
        tt.setDate(Date.valueOf(dpDate.getValue()));
        tt.setCodeStaff(cmbStaff.getValue());
        tt.setCodeHall(cmbHall.getValue());
        tt.setCodeClient(cmbHall.getValue());
        tt.setNumber(cmbId.getValue());
        DataBase.editTaskTable(tt);
        topPane.setDisable(false);
        bottomPane.setVisible(false);
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnDelete.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
}
