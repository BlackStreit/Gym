package com.example.lab5.Controllers;

import com.example.lab5.Classes.Service;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServiceEditController implements Initializable {
    public ComboBox <Integer> cmbId = new ComboBox();
    public TextArea txtInfo;
    public Button btnFound;
    public TextField txtTitle;
    public TextField txtCost;
    public AnchorPane pane;
    public Button btnAdd;
    public Button btnClose;
    public AnchorPane topPane;
    public Label errorLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }


    public void cmbIdSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundService(cmbId.getValue());
        txtInfo.setText(info.toString());
    }

    public void btnFoundClick(ActionEvent actionEvent) {
        pane.setVisible(true);
        topPane.setDisable(true);
        var info = DataBase.foundService(cmbId.getValue());
        txtTitle.setText(info.getTitle());
        txtCost.setText(String.valueOf(info.getCost()));
    }

    public void btnAddClick(ActionEvent actionEvent) {
        var ser = new Service();
        try{
            ser.setTitle(txtTitle.getText());
            try{
                int cost = Integer.parseInt(txtCost.getText());
                ser.setCost(cost);
            }
            catch (Exception e){
                errorLog.setText("Вы ввели некорректное значение стоимости");
                return;
            }
            ser.setId(cmbId.getValue());
            DataBase.editService(ser);
            errorLog.setText("Услуга успешно изменена");
            txtTitle.setText("");
            txtCost.setText("");
            pane.setVisible(false);
            topPane.setDisable(false);
        }
        catch (Exception ex){
            errorLog.setText(ex.getMessage());
        }
        var info = DataBase.foundService(cmbId.getValue());
        txtTitle.setText(info.getTitle());
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnAdd.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
    void init(){
        var ids = DataBase.getServiceId();
        cmbId.setItems(ids);
        if(ids.size()>=1) {
            cmbId.setValue(ids.get(0));
            txtInfo.setText(DataBase.foundService(cmbId.getValue()).toString());
        }
        else{
            txtInfo.setText("Все залы удалены");
            cmbId.setDisable(true);
        }
    }
}
