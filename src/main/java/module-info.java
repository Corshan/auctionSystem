module com.example.auctionsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens main to javafx.fxml, xstream;
    opens controllers to javafx.fxml, xstream;
    opens models to xstream;
    opens utils to xstream;
    exports main;
    exports controllers;
    exports models;
    exports utils;
}