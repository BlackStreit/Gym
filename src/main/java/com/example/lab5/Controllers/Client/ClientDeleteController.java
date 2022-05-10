package com.example.lab5.Controllers.Client;

import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientDeleteController implements Initializable {
    public ComboBox<Integer> cmbIds = new ComboBox<>();
    public Button btnDelete;
    public Button btnClose;
    public TextArea txtInfo;
    public Label errorLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void cmbIdsSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundClient(cmbIds.getValue());
        txtInfo.setText(info.toString());
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        DataBase.deleteClient(cmbIds.getValue());
        errorLog.setText("Клиент удален");
        txtInfo.setText(DataBase.foundClient(cmbIds.getValue()).toString());
        init();
    }

    public void btnCloseClose(ActionEvent actionEvent) throws IOException {
        Stage totalStage = (Stage) btnClose.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        totalStage.close();
        stage.show();
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
}
