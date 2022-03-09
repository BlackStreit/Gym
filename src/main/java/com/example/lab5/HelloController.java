package com.example.lab5;

import com.example.lab5.Classes.Client;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //Работа с клиентом
    public TableView<Client> tvlClient;
    public TableColumn<Integer, Client> tcClientId;
    public TableColumn<Integer, Client> tcStaffId;
    public TableColumn<String, Client> tcName;
    public TableColumn<String, Client> tcPatronymic;
    public TableColumn<String, Client> tcSurname;
    public TableColumn<String, Client> tcAddress;
    public TableColumn<String, Client> tcPhoneNumber;
    public Button btnClientAdd;
    public Button btnClientEdit;
    public Button btnClientDelete;
    
    //Работа с абониментом
    public TableView tvClubCard;
    public TableColumn tcCardId;
    public TableColumn tcServiceId;
    public TableColumn tcClientCardId;
    public TableColumn tcPrice;
    public TableColumn tcStartDate;
    public TableColumn tcEndCard;
    public Button btnCardAdd;
    public Button btnCardEdit;
    public Button btnCardDelete;


    public void btnClientAddClick(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnClientEditClick(ActionEvent actionEvent) {
    }

    public void btnClientDeleteClick(ActionEvent actionEvent) {
    }

    public void btnCardAddClick(ActionEvent actionEvent) {
    }

    public void btnCardEditClick(ActionEvent actionEvent) {
    }

    public void btnCardDeleteClick(ActionEvent actionEvent) {
    }
}