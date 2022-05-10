package com.example.lab5.Controllers.Hall;

import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HallDeleteController  implements Initializable {
    public ComboBox<Integer> cmbId = new ComboBox();
    public Button btnDelete;
    public Button btnClose;
    public Text errorLog;
    public TextArea txtGetinfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        DataBase.deleteHall(cmbId.getValue());
        errorLog.setText("Зал удален");
        txtGetinfo.setText(DataBase.foundHall(cmbId.getValue()).toString());
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
    void init(){
        var ids = DataBase.getHallId();
        cmbId.setItems(ids);
        if(ids.size()>=1) {
            cmbId.setValue(ids.get(0));
            txtGetinfo.setText(DataBase.foundHall(cmbId.getValue()).toString());
        }
        else{
            errorLog.setText("Все залы удалены");
            cmbId.setDisable(true);
        }

    }

    public void cmbIdSwitch(ActionEvent actionEvent) {
        var info = DataBase.foundHall(cmbId.getValue());
        txtGetinfo.setText(info.toString());
    }
}
