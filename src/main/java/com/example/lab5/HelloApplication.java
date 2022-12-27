package com.example.lab5;

import com.example.lab5.Classes.Hall;
import com.example.lab5.DataBase.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataBase.createDataBase();
        DataBase.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Главная страница");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}