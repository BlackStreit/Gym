package com.example.lab5.Controllers;

import com.example.lab5.Classes.Hall;
import com.example.lab5.DataBase.DataBase;
import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HallEditController implements Initializable {
    public ComboBox<Integer> cmbId = new ComboBox();
    public Text errorLog;
    public TextField tfTitle;
    public Button btnEdit;
    public Button btnFoundHall;
    public Button btnClose;
    public AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void btnEditClick(ActionEvent actionEvent) {
        try {
            var hall = new Hall();
            hall.setHallId(cmbId.getValue());
            hall.setHallName(tfTitle.getText());
            DataBase.editHall(hall);
            cmbId.setDisable(false);
            btnFoundHall.setDisable(false);
            pane.setVisible(false);
            errorLog.setText("Успех");
        } catch (Exception e) {
            errorLog.setText("Пустое значение");
        }
    }

    public void btnFoundHallClick(ActionEvent actionEvent) {
        var hall = DataBase.foundHall(cmbId.getValue());
        cmbId.setDisable(true);
        btnFoundHall.setDisable(true);
        tfTitle.setText(hall.getHallName());
        pane.setVisible(true);
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
        }
        else{
            errorLog.setText("Нет залов");
            cmbId.setDisable(true);
        }
    }
}
