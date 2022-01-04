package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Driver;
import models.Bidder;
import utils.ConnectedList;
import utils.Utilities;

import java.util.Locale;

public class BiddersViewController {

    public static Bidder currentBidder;

    @FXML
    TextField addBidderName, addBidderAddress, addBidderPhone, addBidderEmail;

    @FXML
    TextField bidderName, bidderAddress;

    @FXML
    ListView<Bidder> bidderListView;

    @FXML
    Label errorMessage;

    @FXML
    ChoiceBox<String> sortChoiceBox;

    public void initialize() {
        for (Bidder bidder : Driver.auctionAPI.getBidders()) {
            bidderListView.getItems().add(bidder);
        }
        sortChoiceBox.getItems().addAll("A-Z", "Z-A");
    }

    public void switchToMainView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void switchToBidderInfoView(MouseEvent mouseEvent) throws Exception {
        if (mouseEvent.getClickCount() == 2) {
            currentBidder = bidderListView.getItems().get(bidderListView.getSelectionModel().getSelectedIndex());
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("bidder-info-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Driver.mainStage.setScene(scene);
            Driver.mainStage.show();
        }
    }

    public void addBidder() {
        if (Utilities.onlyContainsNumbers(addBidderPhone.getText())) {
            Bidder bidder = new Bidder(addBidderName.getText(), addBidderAddress.getText(), addBidderPhone.getText(), addBidderEmail.getText());
            Driver.auctionAPI.addBidder(bidder);
            bidderListView.getItems().add(bidder);
            errorMessage.setText("");
            clearBidderTextFields();
        } else {
            errorMessage.setText("Invalid");
        }
    }

    public void sortBidders() {
        Driver.auctionAPI.getBidders().mergeSort((a, b) -> a.getName().compareTo(b.getName()));
        bidderListView.getItems().clear();
        for (Bidder bidder : Driver.auctionAPI.getBidders()) {
            bidderListView.getItems().add(bidder);
        }
    }

    public void searchBidders() {
        ConnectedList<Bidder> bidders = new ConnectedList<>();
        if (bidderAddress.getText().equals("")) {
            for (Bidder bidder : Driver.auctionAPI.getBidders()) {
                if (bidder.getName().toLowerCase().contains(bidderName.getText().toLowerCase())) {
                    bidders.add(bidder);
                }
            }
        } else if (bidderName.getText().equals("")) {
            for (Bidder bidder : Driver.auctionAPI.getBidders()) {
                if (bidder.getAddress().toLowerCase().contains(bidderAddress.getText().toLowerCase())) {
                    bidders.add(bidder);
                }
            }
        } else {
            for (Bidder bidder : Driver.auctionAPI.getBidders()) {
                if (bidder.getName().toLowerCase().contains(bidderName.getText().toLowerCase()) && bidder.getAddress().toLowerCase().contains(bidderAddress.getText().toLowerCase())) {
                    bidders.add(bidder);
                }
            }
        }

        try {
            if (sortChoiceBox.getValue().equals("A-Z")) {
                bidders.mergeSort((a, b) -> a.getName().compareTo(b.getName()));
            } else if (sortChoiceBox.getValue().equals("Z-A")) {
                bidders.mergeSort((a, b) -> b.getName().compareTo(a.getName()));
            }
        }catch (Exception e){

        }

        bidderListView.getItems().clear();
        for (Bidder bidder : bidders) {
            bidderListView.getItems().add(bidder);
        }
    }

    public void clearBidderTextFields() {
        addBidderName.clear();
        addBidderAddress.clear();
        addBidderPhone.clear();
        addBidderEmail.clear();
    }

    public void save() {
        try {
            Driver.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            Driver.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        Driver.auctionAPI.clear();
        bidderListView.getItems().clear();
    }
}
