package models;

import utils.ConnectedList;

public class AuctionLot {

    private String title;
    private String description;
    private String type;
    private String originDate;
    private float price;
    private String imageURL;
    private String saleDate;
    private String saleTime;
    private float salePrice;
    private ConnectedList<Bid> bids;

    public AuctionLot(String title, String description, String type, String originDate, float price, String imageURL) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.originDate = originDate;
        this.price = price;
        this.imageURL = imageURL;
        this.saleDate = "Not Sold";
        this.saleTime = "Not Sold";
        this.salePrice = 0;
        this.bids = new ConnectedList<>();
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

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
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
