package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.Driver;
import models.AuctionLot;
import models.Bid;
import models.Bidder;
import utils.ConnectedList;

import java.util.Optional;

public class AuctionAPI {

    private ConnectedList<AuctionLot> soldItems;
    private ConnectedList<AuctionLot> unsoldItems;
    private ConnectedList<Bidder> bidders;

    public AuctionAPI(){
        this.soldItems = new ConnectedList<>();
        this.unsoldItems = new ConnectedList<>();
        this.bidders = new ConnectedList<>();
    }

    public void addBidder(Bidder bidder){
        bidders.add(bidder);
    }

    public void addAuctionLot(AuctionLot auctionLot){unsoldItems.add(auctionLot);}

    public void removeBidder(Bidder bidder){
        bidders.remove(bidder);
    }

    public void removeAuctionLot(AuctionLot auctionLot){unsoldItems.remove(auctionLot);}

    public void addBidToLot(){

    }

    public void findBidder(String name){

    }

    public void clear(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset System");
        alert.setHeaderText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            soldItems.clear();
            unsoldItems.clear();
            bidders.clear();
        }
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
}
