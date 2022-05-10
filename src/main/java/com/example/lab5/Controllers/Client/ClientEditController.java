package com.example.lab5.Controllers.Client;

import com.example.lab5.Classes.Client;
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

public class ClientEditController implements Initializable {
    public AnchorPane topPane;
    public ComboBox<Integer> cmbIds = new ComboBox<>();
    public Button btnFound;
    public TextArea txtInfo;
    public AnchorPane bottomPane;
    public TextField txtName;
    public TextField txtSurname;
    public TextField txtLastname;
    public TextField txtPhone;
    public TextField txtAddress;
    public Button btnEdit;
    public Button btnClose;
    public Label errorLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        var ids = DataBase.getClientIds();
        cmbIds.setItems(ids);
        if(ids.size()>=1) {
            cmbIds.setValue(ids.get(0));
            txtInfo.setText(DataBase.foundClient(cmbIds.getValue()).toString());
        }
        else{
            txtInfo.setText("Все клиенты удалены");
            cmbIds.setDisable(true);
        }
    }

    public void cmbIdsSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundClient(cmbIds.getValue());
        txtInfo.setText(info.toString());
    }

    public void btnFoundClick(ActionEvent actionEvent) {
        topPane.setDisable(true);
        bottomPane.setVisible(true);
        var client = DataBase.foundClient(cmbIds.getValue());
        txtAddress.setText(client.getAddress());
        txtLastname.setText(client.getSurname());
        txtName.setText(client.getName());
        txtSurname.setText(client.getPatronymic());
        txtPhone.setText(client.getPhoneNumber());
    }

    public void btnEditClick(ActionEvent actionEvent) {
        var client = new Client();
        if(txtName.getText().length()==0){
            errorLog.setText("Вы не ввели имя");
            return;
        }
        client.setName(txtName.getText());
        if(txtSurname.getText().length()==0){
            errorLog.setText("Вы не ввели отчество");
            return;
        }
        client.setPatronymic(txtSurname.getText());
        if(txtLastname.getText().length()==0){
            errorLog.setText("Вы не ввели фамилию");
            return;
        }
        client.setSurname(txtLastname.getText());
        if(txtAddress.getText().length() == 0){
            errorLog.setText("Вы не ввели адрес");
            return;
        }
        client.setAddress(txtAddress.getText());
        try{
            if(txtPhone.getText().length() != 11 && txtPhone.getText().length() != 6){
                throw new Exception();
            }
            int phone = Integer.parseInt(txtPhone.getText());
            client.setPhoneNumber(String.valueOf(phone));
        }
        catch (Exception e){
            errorLog.setText("Вы ввели некорректное значение телефона");
            return;
        }
        client.setClientId(cmbIds.getValue());
        DataBase.editClient(client);
        txtPhone.setText("");
        txtAddress.setText("");
        txtLastname.setText("");
        txtSurname.setText("");
        txtName.setText("");
        errorLog.setText("Сотрудник успешно изменен");
        topPane.setDisable(false);
        bottomPane.setVisible(false);
        var info = DataBase.foundClient(cmbIds.getValue());
        txtInfo.setText(info.toString());
    }

    public void btnCloseClick(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnClose.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
    }
}
