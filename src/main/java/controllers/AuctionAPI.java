package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.Driver;
import models.AuctionLot;
import models.Bid;
import models.Bidder;
import utils.ConnectedList;
import utils.HashTable;

import java.util.Comparator;
import java.util.Optional;

public class AuctionAPI {

    private ConnectedList<AuctionLot> soldItems;
    private ConnectedList<AuctionLot> unsoldItems;
    private ConnectedList<Bidder> bidders;
    private HashTable<Bidder> bidderHashTable;

    public AuctionAPI(){
        this.soldItems = new ConnectedList<>();
        this.unsoldItems = new ConnectedList<>();
        this.bidders = new ConnectedList<>();
        this.bidderHashTable = new HashTable<>(50);
    }

    public void addBidder(Bidder bidder){
        bidders.add(bidder);
        bidderHashTable.add(Integer.parseInt(bidder.getPhone()), bidder);
    }

    public void addAuctionLot(AuctionLot auctionLot){unsoldItems.add(auctionLot);}

    public void removeBidder(Bidder bidder){
        bidders.remove(bidder);
        bidderHashTable.remove(Integer.parseInt(bidder.getPhone()), bidder);
    }

    public void updateHashTable(Bidder bidder, String oldNumber, String newNumber){
        bidderHashTable.remove(Integer.parseInt(oldNumber), bidder);
        bidderHashTable.add(Integer.parseInt(newNumber), bidder);
    }

    public void removeAuctionLot(AuctionLot auctionLot){unsoldItems.remove(auctionLot);}


    public Bidder findBidder(String phone){
        return bidderHashTable.get(Integer.parseInt(phone));
//        for (Bidder bidder : bidders){
//            if (bidder.getName().equalsIgnoreCase(name)){
//                return bidder;
//            }
//        }
//        return null;
    }

    public void clear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset System");
        alert.setHeaderText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            soldItems.clear();
            unsoldItems.clear();
            bidders.clear();
            try {
             switchToMainView();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void switchToMainView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public ConnectedList<AuctionLot> getSoldItems() {
        return soldItems;
    }

    public ConnectedList<AuctionLot> getUnsoldItems() {
        return unsoldItems;
    }

    public ConnectedList<Bidder> getBidders() {
        return bidders;
    }

    public HashTable<Bidder> getBidderHashTable() {
        return bidderHashTable;
    }
}
