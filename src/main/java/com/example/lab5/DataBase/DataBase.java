package com.example.lab5.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.lab5.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBase {
    public static Connection connection;
    public static Statement statement;

    private static void deleteTable(){
        try {
            statement.executeUpdate("DROP TABLE Clients");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("DROP TABLE Hall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("DROP TABLE Staff");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("DROP TABLE ClubCard");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("DROP TABLE TaskTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("DROP TABLE Service");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDataBase() {
        try {
            Class.forName("org.sqlite.JDBC"); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection("jdbc:sqlite:GymDataBase");//соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }
    public static void createTable(){
        //deleteTable();
        String sql = "CREATE TABLE IF NOT EXISTS Clients (\n" +
                "clientID integer PRIMARY KEY AUTOINCREMENT,\n" +
                "address text,\n" +
                "phone text,\n" +
                "name text,\n" +
                "patronymic text,\n" +
                "surname text\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE IF NOT EXISTS Staff (\n" +
                "staffId integer PRIMARY KEY AUTOINCREMENT,\n" +
                "address text,\n" +
                "phone text,\n" +
                "experience integer,\n" +
                "name text,\n" +
                "patronymic text,\n" +
                "surname text,\n" +
                "salary integer\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE IF NOT EXISTS Hall (\n" +
                "hallId integer PRIMARY KEY AUTOINCREMENT,\n" +
                "title text\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE IF NOT EXISTS ClubCard (\n" +
                "CardId integer PRIMARY KEY AUTOINCREMENT,\n" +
                "clientId integer,\n" +
                "serviceId integer,\n" +
                "startCard text,\n" +
                "endCard text,\n" +
                "price integer\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE IF NOT EXISTS TaskTable (\n" +
                "taskTableId integer PRIMARY KEY AUTOINCREMENT,\n" +
                "staffId integer,\n" +
                "hallId integer,\n" +
                "clientId integer,\n" +
                "dateLesson text,\n" +
                "duration integer\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE IF NOT EXISTS Service (\n" +
                "serviceId integer PRIMARY KEY AUTOINCREMENT,\n" +
                "title text,\n" +
                "price integer\n" +
                ");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Метод для добавления зала
    public static void addHall(String title){
        //Команда для добавления
        String sql = "INSERT INTO Hall (title) VALUES ('"+title+"');";
        try {
            //Стейтмент отправляет команду
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Получить список залов
    public static ObservableList<Hall> getHall(){
        //Специальный лист, который записывается в таблицу
        ObservableList<Hall> halls =FXCollections.observableArrayList();
        //Запрос для выдачи
        String sql = "SELECT * FROM Hall;";
        try {
            //ResultSet хранит данные их таблицы БД
            ResultSet resultSet = statement.executeQuery(sql);
            //Пока есть что записывать
            while (resultSet.next()){
                //Создать новый холл
                var h = new Hall();
                //Присовить холу Название = выдать текст из записи из колоники     //Столбец с id залов
                h.setHallName(resultSet.getString("title"));
                h.setHallId(resultSet.getInt("hallId"));
                //Полжить зал в список
                halls.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return halls;
    }
    //Найти зал по id
    public static Hall foundHall(int id){
        var hall = new Hall();
        String sql = "SELECT * FROM Hall where hallId  = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                hall.setHallName(resultSet.getString("title"));
                hall.setHallId(resultSet.getInt("hallId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hall;
    }
    //изменить зал
    public static void editHall(Hall hall){
        String sql = "UPDATE Hall SET title = '"+hall.getHallName()+"' where hallId = " + hall.getHallId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Удалить зал по id
    public static void deleteHall(int id){
        try {
            statement.executeUpdate("delete from TaskTable where hallId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("delete from Hall where hallId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Получить список все id залов
    public static ObservableList<Integer> getHallId(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        var sql = "select hallId from Hall;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("hallId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static void addClients(Client client){
        String sql ="INSERT INTO Clients ( address, phone, name, patronymic, surname) VALUES ('"+client.getAddress()+"', " +
                "'"+client.getPhoneNumber()+"', '"+client.getName()+"', '"+client.getPatronymic()+"', '"+client.getSurname()+"');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Client> getClient(){
        ObservableList<Client> clients =FXCollections.observableArrayList();
        String sql = "select * from Clients;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                var c = new Client();
                c.setAddress(resultSet.getString("address"));
                c.setClientId(resultSet.getInt("clientID"));
                c.setName(resultSet.getString("name"));
                c.setPatronymic(resultSet.getString("patronymic"));
                c.setSurname(resultSet.getString("surname"));
                c.setPhoneNumber(resultSet.getString("phone"));
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    public static Client foundClient(int id){
        var client = new Client();
        String sql = "select * from Clients where clientID = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                client.setAddress(resultSet.getString("address"));
                client.setClientId(resultSet.getInt("clientID"));
                client.setName(resultSet.getString("name"));
                client.setPatronymic(resultSet.getString("patronymic"));
                client.setSurname(resultSet.getString("surname"));
                client.setPhoneNumber(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    public static void editClient(Client client){
        String sql ="UPDATE Clients SET address= '"+client.getAddress()+"', " +
                "phone = '"+client.getPhoneNumber()+"', name = '"+client.getName()+"', patronymic = '"+client.getPatronymic()+"', " +
                "surname = '"+client.getSurname()+"' where clientID = " + client.getClientId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteClient(int id){
        try {
            statement.executeUpdate("delete from Clients where clientID = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Integer> getClientIds(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        var sql = "select clientID from Clients;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("clientID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static void addStaff(Staff staff){
        String sql ="INSERT INTO Staff (address, phone, name, patronymic, surname, experience, salary ) VALUES ('"+staff.getAddress()+"', " +
                "'"+staff.getPhoneNumber()+"', '"+staff.getName()+"', '"+staff.getPatronymic()+"', '"+staff.getSurname()+"', "+staff.getWorkExperience()+", " +
                " "+staff.getSalary()+");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Staff> getStaff(){
        ObservableList<Staff> staffs =FXCollections.observableArrayList();
        String sql = "select * from Staff;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                var s = new Staff();
                s.setAddress(resultSet.getString("address"));
                s.setSalary(resultSet.getInt("salary"));
                s.setName(resultSet.getString("name"));
                s.setPatronymic(resultSet.getString("patronymic"));
                s.setSurname(resultSet.getString("surname"));
                s.setStaffId(resultSet.getInt("staffID"));
                s.setPhoneNumber(resultSet.getString("phone"));
                s.setWorkExperience(resultSet.getInt("experience"));
                staffs.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }
    public static Staff foundStaff(int id){
        var staff = new Staff();
        String sql = "select * from Staff where staffId = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                staff.setAddress(resultSet.getString("address"));
                staff.setSalary(resultSet.getInt("salary"));
                staff.setName(resultSet.getString("name"));
                staff.setPatronymic(resultSet.getString("patronymic"));
                staff.setSurname(resultSet.getString("surname"));
                staff.setStaffId(resultSet.getInt("staffID"));
                staff.setPhoneNumber(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }
    public static void editStaff(Staff staff){
        String sql ="UPDATE Staff SET address= '"+staff.getAddress()+"', " +
                "phone = '"+staff.getPhoneNumber()+"', name = '"+staff.getName()+"', patronymic = '"+staff.getPatronymic()+"', " +
                "surname = '"+staff.getSurname()+"', salary = "+ staff.getSalary()+" where staffID = " + staff.getStaffId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteStaff(int id){
        try {
            statement.executeUpdate("delete from TaskTable where staffId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("delete from Staff where staffId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Integer> getStaffIds(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        var sql = "select staffId from Staff;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("staffId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static void addClubCard(ClubCard clubCard){
        var sql = "INSERT INTO ClubCard (clientId, serviceId, startCard, endCard, price ) VALUES ("+clubCard.getClientId()+", " +
                " "+clubCard.getServiceId()+", '"+clubCard.getStartCard().toString()+"', '"+ clubCard.getEndCard().toString()+"', "+clubCard.getPrice()+");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<ClubCard> getClubCard(){
        ObservableList<ClubCard> clubCards = FXCollections.observableArrayList();
        var sql = "SELECT * FROM ClubCard;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                var cc = new ClubCard();
                cc.setCardId(resultSet.getInt("CardId"));
                cc.setClientId(resultSet.getInt("clientId"));
                cc.setStartCard(Date.valueOf(resultSet.getString("startCard")));
                cc.setEndCard(Date.valueOf(resultSet.getString("endCard")));
                cc.setPrice(resultSet.getInt("price"));
                cc.setServiceId(resultSet.getInt("serviceId"));
                clubCards.add(cc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubCards;
    }
    public static ClubCard foundClubCard(int id){
        var cc = new ClubCard();
        var sql = "SELECT * FROM ClubCard where CardId = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                cc.setCardId(resultSet.getInt("CardId"));
                cc.setClientId(resultSet.getInt("clientId"));
                cc.setStartCard(Date.valueOf(resultSet.getString("startCard")));
                cc.setEndCard(Date.valueOf(resultSet.getString("endCard")));
                cc.setPrice(resultSet.getInt("price"));
                cc.setServiceId(resultSet.getInt("serviceId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }
    public static void editClubCard(ClubCard clubCard){
        var sql = "UPDATE ClubCard SET  clientId = "+clubCard.getCardId()+", serviceId = "+clubCard.getServiceId()+", " +
                "startCard = '"+clubCard.getStartCard().toString()+"', endCard = '"+clubCard.getEndCard().toString()+"', price = "+clubCard.getPrice()+" " +
                "where CardId = "+clubCard.getCardId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteClubCard(int id){
        try {
            statement.executeUpdate("delete from ClubCard where CardId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Integer> getClubCardId(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        var sql = "select CardId from ClubCard;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("CardId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static void addTaskTable(TaskTable tt){
        var sql = "INSERT INTO TaskTable (staffId, hallId, clientId, dateLesson, duration) VALUES " +
                "("+tt.getCodeStaff()+", "+tt.getCodeHall()+", "+tt.getCodeClient()+", '"+tt.getDate()+"', "+tt.getDuration()+");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<TaskTable> getTaskTable(){
        ObservableList<TaskTable> taskTables = FXCollections.observableArrayList();
        var sql = "select * from TaskTable; ";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                var tt = new TaskTable();
                tt.setCodeClient(resultSet.getInt("clientId"));
                tt.setCodeHall(resultSet.getInt("hallId"));
                tt.setCodeStaff(resultSet.getInt("staffId"));
                tt.setDate(Date.valueOf(resultSet.getString("dateLesson")));
                tt.setNumber(resultSet.getInt("taskTableId"));
                tt.setDuration(resultSet.getInt("duration"));
                taskTables.add(tt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskTables;
    }
    public static TaskTable foundTaskTable(int id){
        var tt = new TaskTable();
        var sql = "select * from TaskTable where taskTableId = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tt.setCodeClient(resultSet.getInt("clientId"));
                tt.setCodeHall(resultSet.getInt("hallId"));
                tt.setCodeStaff(resultSet.getInt("staffId"));
                tt.setDate(Date.valueOf(resultSet.getString("dateLesson")));
                tt.setNumber(resultSet.getInt("taskTableId"));
                tt.setDuration(resultSet.getInt("duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tt;
    }
    public static void editTaskTable(TaskTable tt){
        var sql = "UPDATE TaskTable SET staffId = "+tt.getCodeStaff()+", hallId = "+tt.getCodeHall()+", clientId = "+tt.getCodeClient()+", " +
                " dateLesson = '"+tt.getDate()+"', duration = "+tt.getDuration()+" where taskTableId = "+tt.getNumber()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTaskTable(int id){
        try {
            statement.executeUpdate("delete from TaskTable where taskTableId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Integer> getTaskTableId(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        var sql = "select taskTableId from TaskTable;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("taskTableId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static void addService(Service service){
        var sql = "INSERT INTO Service (title, price) VALUES ('"+service.getTitle()+"', "+service.getCost()+");";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Service> getService(){
        ObservableList<Service> services = FXCollections.observableArrayList();
        var sql = "select * from Service;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                var s = new Service();
                s.setId(resultSet.getInt("serviceId"));
                s.setCost(resultSet.getInt("price"));
                s.setTitle(resultSet.getString("title"));
                services.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }
    public static Service foundService(int id){
        var s = new Service();
        var sql = "select * from Service where serviceId = "+id+";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                s.setId(resultSet.getInt("serviceId"));
                s.setCost(resultSet.getInt("price"));
                s.setTitle(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void editService(Service service){
        var sql = "UPDATE Service SET title = '"+service.getTitle()+"', price = "+service.getCost()+" where serviceId = "+service.getId()+";";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteService(int id){
        try {
            statement.executeUpdate("delete from ClubCard where serviceId = " + id+";");
            statement.executeUpdate("delete from Service where serviceId = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Integer> getServiceId(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        var sql = "select serviceId from Service;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("serviceId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }
}
