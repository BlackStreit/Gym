package com.example.lab5.Controllers.ClubCard;

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

public class ClubCardDeleteController implements Initializable {
    public ComboBox<Integer> cmbIds = new ComboBox<>();
    public Label errorLog;
    public TextArea txtInfo;
    public Button btnDelete;
    public Button btnClose;

    void init(){
        var ids = DataBase.getClubCardId();
        cmbIds.setItems(ids);
        if(ids.size()>=1) {
            cmbIds.setValue(ids.get(0));
            txtInfo.setText(DataBase.foundClubCard(cmbIds.getValue()).toString());
        }
        else{
            txtInfo.setText("Все абонементы удалены");
            cmbIds.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        DataBase.deleteClubCard(cmbIds.getValue());
        errorLog.setText("Сотрудник удален");
        txtInfo.setText(DataBase.foundClubCard(cmbIds.getValue()).toString());
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

    public void cmbIdsSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundClubCard(cmbIds.getValue());
        txtInfo.setText(info.toString());
    }
}
