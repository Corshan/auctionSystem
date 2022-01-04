package models;

import utils.ConnectedList;

import java.time.LocalDate;
import java.time.LocalTime;

public class AuctionLot {

    private String title;
    private String description;
    private String type;
    private String originDate;
    private float price;
    private String imageURL;
    private LocalDate saleDate;
    private LocalTime saleTime;
    private float salePrice;
    private ConnectedList<Bid> bids;

    public AuctionLot(String title, String description, String type, String originDate, float price, String imageURL) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.originDate = originDate;
        this.price = price;
        this.imageURL = imageURL;
        this.bids = new ConnectedList<>();
    }

    public int addBid(Bid bid) {
        if (bid.getAmount() > price) {
            if (bids.size() == 0) {
                bids.add(bid);
                return 1;
            }
            if (bid.getAmount() > bids.get(bids.size()-1).getAmount()) {
                bids.add(bid);
                return 1;
            }

            return 0;
        }
        return -1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginDate() {
        return originDate;
    }

    public void setOriginDate(String originDate) {
        this.originDate = originDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public LocalTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalTime saleTime) {
        this.saleTime = saleTime;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public ConnectedList<Bid> getBids() {
        return bids;
    }

    public void setBids(ConnectedList<Bid> bids) {
        this.bids = bids;
    }

    @Override
    public String toString() {
        return title + " " + description;
    }
}
