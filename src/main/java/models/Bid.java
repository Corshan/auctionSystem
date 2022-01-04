package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Bid {

    private float amount;
    private LocalDate date;
    private LocalTime time;
    private boolean winningBid;

    public Bid(float amount, LocalDate date, LocalTime time) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isWinningBid() {
        return winningBid;
    }

    public void setWinningBid(boolean winningBid) {
        this.winningBid = winningBid;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "amount=" + amount +
                ", date=" + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ", time=" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                '}';
    }
}
