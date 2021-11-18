package main;

import controllers.AuctionAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {

    public static Stage mainStage;
    public static AuctionAPI auctionAPI;

    public void start(Stage stage) throws IOException {
        mainStage = stage;
        auctionAPI = new AuctionAPI();

        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setResizable(false);
        mainStage.setTitle("Auction");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
