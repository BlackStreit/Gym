module com.example.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab5 to javafx.fxml;
    exports com.example.lab5;
    exports com.example.lab5.Controllers;
    opens com.example.lab5.Controllers to javafx.fxml;
    exports com.example.lab5.Classes;
    opens com.example.lab5.Classes to javafx.fxml;
}