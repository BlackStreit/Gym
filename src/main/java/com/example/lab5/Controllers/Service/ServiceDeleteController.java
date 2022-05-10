package com.example.lab5.Controllers.Service;

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

public class ServiceDeleteController implements Initializable {
    public ComboBox <Integer> cmbId = new ComboBox();
    public Button btnDelete;
    public Button btnClose;
    public TextArea txtInfo;
    public Label errolLog;

    public void cmbIdSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundService(cmbId.getValue());
        txtInfo.setText(info.toString());
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        DataBase.deleteService(cmbId.getValue());
        errolLog.setText("Услуга удалена");
        txtInfo.setText(DataBase.foundService(cmbId.getValue()).toString());
        init();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    void init(){
        var ids = DataBase.getServiceId();
        cmbId.setItems(ids);
        if(ids.size()>=1) {
            cmbId.setValue(ids.get(0));
            txtInfo.setText(DataBase.foundService(cmbId.getValue()).toString());
        }
        else{
            txtInfo.setText("Все услуги удалены");
            cmbId.setDisable(true);
        }
    }
}
