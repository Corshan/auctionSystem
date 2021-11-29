package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Driver;
import models.Bidder;
import utils.Utilities;

public class BiddersViewController {

    public static Bidder currentBidder;

    @FXML
    TextField addBidderName, addBidderAddress, addBidderPhone, addBidderEmail;

    @FXML
    ListView<Bidder> bidderListView;

    public void initialize(){
        for (Bidder bidder : Driver.auctionAPI.getBidders()){
            bidderListView.getItems().add(bidder);
        }
    }

    public void switchToMainView() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void switchToBidderInfoView(MouseEvent mouseEvent) throws Exception{
        if (mouseEvent.getClickCount() == 2){
            currentBidder = bidderListView.getItems().get(bidderListView.getSelectionModel().getSelectedIndex());
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("bidder-info-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Driver.mainStage.setScene(scene);
            Driver.mainStage.show();
        }
    }

    public void addBidder(){
            if (Utilities.onlyContainsNumbers(addBidderPhone.getText())){
                Bidder bidder = new Bidder(addBidderName.getText(), addBidderAddress.getText(), addBidderPhone.getText(), addBidderEmail.getText());
                Driver.auctionAPI.addBidder(bidder);
                bidderListView.getItems().add(bidder);
                clearBidderTextFields();
            }else {
                //TODO
            }
    }

    public void clearBidderTextFields(){
        addBidderName.clear();
        addBidderAddress.clear();
        addBidderPhone.clear();
        addBidderEmail.clear();
    }

    public void save(){
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void load(){
        try {
            Driver.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clear(){
        Driver.auctionAPI.clear();
    }
}
