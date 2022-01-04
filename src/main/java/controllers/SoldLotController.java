package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import main.Driver;
import models.AuctionLot;
import models.Bid;

import java.time.format.DateTimeFormatter;

public class SoldLotController {

    @FXML
    Label titleLabel, descriptionLabel, typeLabel, originDateLabel, priceLabel, imageLabel, saleTimeLabel, salePriceLabel;

    @FXML
    ListView<Bid> bidListView;

    private AuctionLot currentAuctionLot;

    public void initialize() {
        currentAuctionLot = MainViewController.currentAuctionLot;
        titleLabel.setText(currentAuctionLot.getTitle());
        descriptionLabel.setText(currentAuctionLot.getDescription());
        typeLabel.setText(currentAuctionLot.getType());
        originDateLabel.setText(currentAuctionLot.getOriginDate());
        priceLabel.setText(currentAuctionLot.getPrice() + "");
        imageLabel.setText(currentAuctionLot.getImageURL());
        saleTimeLabel.setText(currentAuctionLot.getSaleTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "    " + currentAuctionLot.getSaleDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        salePriceLabel.setText(currentAuctionLot.getSalePrice() + "");
        listBid();
    }

    public void switchToMainView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void switchToBiddersView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("bidders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void listBid() {
        bidListView.getItems().clear();
        currentAuctionLot.getBids().mergeSort((a,b) -> (int) (b.getAmount()-a.getAmount()));
        for (int i = currentAuctionLot.getBids().size() - 1; i >= 0; i--) {
            bidListView.getItems().add(currentAuctionLot.getBids().get(i));
        }
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
    }
}
