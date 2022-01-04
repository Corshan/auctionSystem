package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import main.Driver;
import models.AuctionLot;
import models.Bid;
import models.Bidder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LotInfoController {
    @FXML
    Label titleLabel, descriptionLabel, typeLabel, originDateLabel, priceLabel, imageLabel, saleTimeLabel, salePriceLabel;
    @FXML
    TextField titleTextField, descriptionTextField, typeTextField, priceTextField, imageTextField;
    @FXML
    TextField bidderName, amount;
    @FXML
    DatePicker originDate;
    @FXML
    ToggleButton editButton, addBid;
    @FXML
    HBox editFields, bidHBox;
    @FXML
    ListView<Bid> bidListView;

    private AuctionLot currentAuctionLot;
    private ToggleGroup group;

    public void initialize() {
        currentAuctionLot = MainViewController.currentAuctionLot;
        titleLabel.setText(currentAuctionLot.getTitle());
        descriptionLabel.setText(currentAuctionLot.getDescription());
        typeLabel.setText(currentAuctionLot.getType());
        originDateLabel.setText(currentAuctionLot.getOriginDate());
        priceLabel.setText(currentAuctionLot.getPrice() + "");
        imageLabel.setText(currentAuctionLot.getImageURL());
        saleTimeLabel.setText(currentAuctionLot.getSaleTime() + currentAuctionLot.getSaleDate());
        salePriceLabel.setText(currentAuctionLot.getSalePrice() + "");
        group = new ToggleGroup();
        editButton.setToggleGroup(group);
        addBid.setToggleGroup(group);
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

    public void showEditFields() {
        if (editButton.isSelected()) {
            editFields.setVisible(true);
            bidHBox.setVisible(false);
            titleTextField.setText(currentAuctionLot.getTitle());
            descriptionTextField.setText(currentAuctionLot.getDescription());
            typeTextField.setText(currentAuctionLot.getType());
            try {
                originDate.setValue(LocalDate.parse(currentAuctionLot.getOriginDate()));
            } catch (Exception e) {
                System.out.println(e);
                originDate.setPromptText(currentAuctionLot.getOriginDate());
            }
            priceTextField.setText(currentAuctionLot.getPrice() + "");
            imageTextField.setText(currentAuctionLot.getImageURL());
        } else editFields.setVisible(false);
    }

    public void editBidder() {
        currentAuctionLot.setTitle(titleTextField.getText());
        currentAuctionLot.setDescription(descriptionTextField.getText());
        currentAuctionLot.setType(typeTextField.getText());
        try {
            currentAuctionLot.setOriginDate(originDate.getValue().toString());
        } catch (Exception e) {

        }
        currentAuctionLot.setPrice(Float.parseFloat(priceTextField.getText()));
        currentAuctionLot.setImageURL(imageTextField.getText());

        titleLabel.setText(titleTextField.getText());
        descriptionLabel.setText(descriptionTextField.getText());
        typeLabel.setText(typeTextField.getText());
        try {
            originDateLabel.setText(originDate.getValue().toString());
        }catch (Exception e){

        }
        priceLabel.setText(priceTextField.getText());
        imageLabel.setText(imageTextField.getText());
    }

    public void removeLot() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Lot");
        alert.setHeaderText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Driver.auctionAPI.removeAuctionLot(currentAuctionLot);
            switchToMainView();
        }
    }

    public void showAddBid() {
        if (addBid.isSelected()) {
            bidHBox.setVisible(true);
            editFields.setVisible(false);
        } else {
            bidHBox.setVisible(false);
        }
    }

    public void addBid() {
        Bid bid = new Bid(Float.parseFloat(amount.getText()), java.time.LocalDate.now(), java.time.LocalTime.now());
        Bidder bidder = Driver.auctionAPI.findBidder(bidderName.getText());
        bidder.getBids().add(bid);
        currentAuctionLot.addBid(bid);
        amount.clear();
        bidderName.clear();
        listBid();
    }

    public void listBid() {
        bidListView.getItems().clear();
        for (int i = currentAuctionLot.getBids().size()-1; i >= 0; i--) {
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
