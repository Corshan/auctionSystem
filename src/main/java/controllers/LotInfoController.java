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
import java.time.LocalTime;
import java.util.Optional;

public class LotInfoController {
    @FXML
    Label titleLabel, descriptionLabel, typeLabel, originDateLabel, priceLabel, imageLabel, warningLabel;
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
        } catch (Exception e) {

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

    public void sellLot() {
        try {
            float amount = currentAuctionLot.getBids().get(0).getAmount();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sell Item");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Current Highest Bid: €" + amount);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                currentAuctionLot.getBids().get(0).setWinningBid(true);
                currentAuctionLot.setSalePrice(amount);
                currentAuctionLot.setSaleTime(LocalTime.now());
                currentAuctionLot.setSaleDate(LocalDate.now());
                Driver.auctionAPI.sellAuctionLot(currentAuctionLot);
                FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("sold-item-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Driver.mainStage.setScene(scene);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sell Item");
            alert.setHeaderText("There are no bids on this item");
            alert.show();
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
        try {
            Bid bid = new Bid(Float.parseFloat(amount.getText()), java.time.LocalDate.now(), java.time.LocalTime.now());
            if (Driver.auctionAPI.findBidder(bidderName.getText()) != null) {
                Bidder bidder = Driver.auctionAPI.findBidder(bidderName.getText());
                switch (currentAuctionLot.addBid(bid)) {
                    case -1:
                        warningLabel.setText("Amount below asking price");
                        break;
                    case 0:
                        warningLabel.setText("Amount below current highest bid");
                        break;
                    case 1:
                        bidder.getBids().add(bid);
                        amount.clear();
                        bidderName.clear();
                        listBid();
                        warningLabel.setText("");
                }
            } else {
                warningLabel.setText("Unable to find Bidder");
            }
        } catch (Exception e) {
            System.err.println(e);
            warningLabel.setText("Invalid Amount");
        }
    }

    public void removeBid() {
        Bid bid = bidListView.getSelectionModel().getSelectedItem();
        if (bid != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Bid");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Driver.auctionAPI.removeBid(currentAuctionLot, bid);
                listBid();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Bid");
            alert.setHeaderText("A bid is not selected");
            alert.show();
        }

    }

    public void listBid() {
        bidListView.getItems().clear();
        currentAuctionLot.getBids().mergeSort((a,b) -> (int) (b.getAmount()-a.getAmount()));
        for (int i = 0; i <= currentAuctionLot.getBids().size(); i++) {
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
