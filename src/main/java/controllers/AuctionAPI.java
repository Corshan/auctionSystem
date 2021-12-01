package controllers;

import models.AuctionLot;
import models.Bid;
import models.Bidder;
import utils.ConnectedList;

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

    public void clear(){
        soldItems.clear();
        unsoldItems.clear();
        bidders.clear();
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
