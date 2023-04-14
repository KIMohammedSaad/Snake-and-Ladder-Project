module com.example.snakeladderapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladderapp to javafx.fxml;
    exports com.example.snakeladderapp;
}