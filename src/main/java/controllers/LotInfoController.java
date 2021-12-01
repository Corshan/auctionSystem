package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import main.Driver;
import models.AuctionLot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LotInfoController {
    @FXML
    Label titleLabel, descriptionLabel, typeLabel, originDateLabel, priceLabel, imageLabel, saleTimeLabel, salePriceLabel;
    @FXML
    TextField titleTextField, descriptionTextField, typeTextField, priceTextField, imageTextField;
    @FXML
    DatePicker originDate;
    @FXML
    ToggleButton editButton;
    @FXML
    HBox editFields;

    private AuctionLot currentAuctionLot;

    public void initialize(){
        currentAuctionLot = MainViewController.currentAuctionLot;
        titleLabel.setText(currentAuctionLot.getTitle());
        descriptionLabel.setText(currentAuctionLot.getDescription());
        typeLabel.setText(currentAuctionLot.getType());
        originDateLabel.setText(currentAuctionLot.getOriginDate());
        priceLabel.setText(currentAuctionLot.getPrice() + "");
        imageLabel.setText(currentAuctionLot.getImageURL());
        saleTimeLabel.setText(currentAuctionLot.getSaleTime() + currentAuctionLot.getSaleDate());
        salePriceLabel.setText(currentAuctionLot.getSalePrice() + "");
    }

    public void switchToMainView() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void switchToBiddersView() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("bidders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void showEditFields() {
        if (editButton.isSelected()) {
            editFields.setVisible(true);
            titleTextField.setText(currentAuctionLot.getTitle());
            descriptionTextField.setText(currentAuctionLot.getDescription());
            typeTextField.setText(currentAuctionLot.getType());
            originDate.setValue(LocalDate.parse(currentAuctionLot.getOriginDate()));
            priceTextField.setText(currentAuctionLot.getPrice() + "");
            imageTextField.setText(currentAuctionLot.getImageURL());
        } else editFields.setVisible(false);
    }

    public void editBidder(){
        currentAuctionLot.setTitle(titleTextField.getText());
        currentAuctionLot.setDescription(descriptionTextField.getText());
        currentAuctionLot.setType(typeTextField.getText());
        currentAuctionLot.setOriginDate(originDate.getValue().toString());
        currentAuctionLot.setPrice(Float.parseFloat(priceTextField.getText()));
        currentAuctionLot.setImageURL(imageTextField.getText());

        titleLabel.setText(titleTextField.getText());
        descriptionLabel.setText(descriptionTextField.getText());
        typeLabel.setText(typeTextField.getText());
        originDateLabel.setText(originDate.getValue().toString());
        priceLabel.setText(priceTextField.getText());
        imageLabel.setText(imageTextField.getText());
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
