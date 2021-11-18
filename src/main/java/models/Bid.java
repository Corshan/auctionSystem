package models;

public class Bid {

    private float amount;
    private String date;
    private String time;
    private boolean winningBid;

    public Bid(float amount, String date, String time) {
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.winningBid = false;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isWinningBid() {
        return winningBid;
    }

    public void setWinningBid(boolean winningBid) {
        this.winningBid = winningBid;
    }
}
