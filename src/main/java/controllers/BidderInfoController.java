package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import main.Driver;
import models.Bid;
import models.Bidder;
import utils.Utilities;

import java.util.Optional;

public class BidderInfoController {

    @FXML
    HBox editFields;

    @FXML
    ToggleButton editButton;

    @FXML
    Label nameLabel, phoneLabel, addressLabel, emailLabel, warningLabel;

    @FXML
    TextField nameTextfield, phoneTextField, addressTextField, emailTextField;

    @FXML
    ListView<Bid> bidsListView;

    private Bidder currentBidder;

    public void initialize() {
        currentBidder = BiddersViewController.currentBidder;
        nameLabel.setText(currentBidder.getName());
        phoneLabel.setText(currentBidder.getPhone());
        addressLabel.setText(currentBidder.getAddress());
        emailLabel.setText(currentBidder.getEmail());

        for (Bid bid : currentBidder.getBids()) {
            bidsListView.getItems().add(bid);
        }
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
            nameTextfield.setText(currentBidder.getName());
            phoneTextField.setText(currentBidder.getPhone());
            addressTextField.setText(currentBidder.getAddress());
            emailTextField.setText(currentBidder.getEmail());

        } else editFields.setVisible(false);
    }

    public void editBidder() {
        if (Utilities.onlyContainsNumbers(phoneTextField.getText())) {
            Driver.auctionAPI.updateHashTable(currentBidder, currentBidder.getPhone(), phoneTextField.getText());
            currentBidder.setName(nameTextfield.getText());
            currentBidder.setPhone(phoneTextField.getText());
            currentBidder.setAddress(addressTextField.getText());
            currentBidder.setEmail(emailTextField.getText());

            nameLabel.setText(currentBidder.getName());
            phoneLabel.setText(currentBidder.getPhone());
            addressLabel.setText(currentBidder.getAddress());
            emailLabel.setText(currentBidder.getEmail());

            warningLabel.setText("");
        }else {
            warningLabel.setText("Invalid Phone Number");
        }
    }

    public void removeBidder() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Bidder");
        alert.setHeaderText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Driver.auctionAPI.removeBidder(currentBidder);
            switchToBiddersView();
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
