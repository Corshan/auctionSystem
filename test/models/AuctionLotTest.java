package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionLotTest {

    AuctionLot auctionLot1;
    Bid bid1, bid2, bid3;

    @BeforeEach
    void setUp() {
        auctionLot1 = new AuctionLot("Chair", "Blue Chair", "Chair", "20-19-2020", 100, "None");
        bid1 = new Bid(90, java.time.LocalDate.now(), java.time.LocalTime.now());
        bid2 = new Bid(110, java.time.LocalDate.now(), java.time.LocalTime.now());
        bid3 = new Bid(120, java.time.LocalDate.now(), java.time.LocalTime.now());
    }

    @AfterEach
    void tearDown() {
        auctionLot1 = null;
        bid1 = null;
        bid2 = null;
        bid3 = null;
    }

    @Test
    void addBid(){

    }
}