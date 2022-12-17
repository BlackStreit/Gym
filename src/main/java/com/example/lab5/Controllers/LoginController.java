package com.example.lab5.Controllers;

import com.example.lab5.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.example.lab5.DataBase.DataBase;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button btnIn;
    public TextField txtLogin;
    public TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnInClick(ActionEvent actionEvent) throws IOException {
        var login = txtLogin.getText();
        var password = txtPassword.getText();
        var ans = DataBase.Authorisation(login, password);
        if(ans){
            Stage totalStage = (Stage) btnIn.getScene().getWindow();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Добавить абонемент");
            stage.setScene(scene);
            totalStage.close();
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Не верные данные");
            alert.showAndWait();
        }
    }
}
