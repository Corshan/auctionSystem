module com.example.auctionsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens main to javafx.fxml;
    exports main;
    exports controllers;
    opens controllers to javafx.fxml;
}