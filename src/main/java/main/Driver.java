package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import controllers.AuctionAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

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



    public static void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("VaccinationCentre.xml"));
        out.writeObject(auctionAPI);
        out.close();
    }

    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY); //https://stackoverflow.com/questions/30812293/com-thoughtworks-xstream-security-forbiddenclassexception
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("VaccinationCentre.xml"));
        auctionAPI = (AuctionAPI) is.readObject();
        is.close();
    }
}
