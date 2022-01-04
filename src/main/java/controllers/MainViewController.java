package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Driver;
import models.AuctionLot;
import utils.ConnectedList;

import java.time.temporal.Temporal;
import java.util.Comparator;

public class MainViewController {
    @FXML
    TextField title, description, type, price, image, searchBar;
    @FXML
    DatePicker originDate;
    @FXML
    ListView<AuctionLot> unsoldItems, soldItems;
    @FXML
    AnchorPane addPane;
    @FXML
    Button addButton, searchButton;

    public static AuctionLot currentAuctionLot;

    public void initialize(){
        for (AuctionLot auctionLot: Driver.auctionAPI.getUnsoldItems()){
            unsoldItems.getItems().add(auctionLot);
        }

        for (AuctionLot auctionLot: Driver.auctionAPI.getSoldItems()){
            soldItems.getItems().add(auctionLot);
        }
    }

    public void switchToBiddersView() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("bidders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void switchToItemViewUnsold(MouseEvent mouseEvent) throws Exception{
        if (mouseEvent.getClickCount() == 2) {
            currentAuctionLot = unsoldItems.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("unsold-item-info-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Driver.mainStage.setScene(scene);
        }
    }

    public void switchToItemViewSold(MouseEvent mouseEvent) throws Exception{
        if (mouseEvent.getClickCount() == 2) {
            currentAuctionLot = soldItems.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("unsold-item-info-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Driver.mainStage.setScene(scene);
        }
    }

    @FXML
    public void addAuctionLot(){
        if (originDate.getValue() != null) {
            AuctionLot auctionLot = new AuctionLot(title.getText(), description.getText(), type.getText(), originDate.getValue().toString(), Float.parseFloat(price.getText()), image.getText());
            Driver.auctionAPI.addAuctionLot(auctionLot);
            unsoldItems.getItems().add(auctionLot);
            clearAuctionLotTextFields();
        } else {
            AuctionLot auctionLot = new AuctionLot(title.getText(), description.getText(), type.getText(), "Unknown", Float.parseFloat(price.getText()), image.getText());
            Driver.auctionAPI.addAuctionLot(auctionLot);
            unsoldItems.getItems().add(auctionLot);
            clearAuctionLotTextFields();
        }
    }

    @FXML
    public void toggleAddPane(){
        if(addPane.isVisible()){
            addPane.setVisible(false);
            addButton.setText("Add Auction Lot");
        } else if(!addPane.isVisible()){
            addPane.setVisible(true);
            addButton.setText("Hide");
        }
    }

    @FXML
    public void searchNameAscending(){
        unsoldItems.getItems().clear();
        soldItems.getItems().clear();

        ConnectedList<AuctionLot> unsoldResults = new ConnectedList<>();
        ConnectedList<AuctionLot> soldResults = new ConnectedList<>();

        for(AuctionLot auctionLot: Driver.auctionAPI.getUnsoldItems()){
            if(auctionLot.getTitle().contains(searchBar.getText())){
                unsoldResults.add(auctionLot);
            }
        }

        for(AuctionLot auctionLot: Driver.auctionAPI.getSoldItems()){
            if(auctionLot.getTitle().contains(searchBar.getText())){
                soldResults.add(auctionLot);
            }
        }

        unsoldResults.mergeSort(Comparator.comparing(AuctionLot::getTitle));
        soldResults.mergeSort(Comparator.comparing(AuctionLot::getTitle));

        for (AuctionLot auctionLot: unsoldResults){
            unsoldItems.getItems().add(auctionLot);
        }

        for (AuctionLot auctionLot: soldResults){
            soldItems.getItems().add(auctionLot);
        }
    }

    @FXML
    public void searchNameDescending(){

    }

    public void clearAuctionLotTextFields(){
        title.clear();
        description.clear();
        type.clear();
        price.clear();
        price.clear();
        image.clear();
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
        unsoldItems.getItems().clear();
        soldItems.getItems().clear();
    }
}
