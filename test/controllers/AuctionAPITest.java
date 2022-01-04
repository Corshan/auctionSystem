package controllers;

import models.AuctionLot;
import models.Bidder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionAPITest {

    AuctionAPI auctionAPI;
    AuctionLot auctionLot1;
    Bidder bidder1, bidder2;

    @BeforeEach
    void setUp() {
        auctionAPI = new AuctionAPI();
        auctionLot1 = new AuctionLot("Chair", "You sit on it", "Chair", "Unknown", 0, "image");
        bidder1 = new Bidder("Corey", "5 Fear Street", "5812320", "@email.com");
        bidder2 = new Bidder("Jack", "4 Fear Street", "53420", "@email.com");

        auctionAPI.addBidder(bidder1);
        auctionAPI.addBidder(bidder2);
        auctionAPI.addAuctionLot(auctionLot1);
    }

    @AfterEach
    void tearDown() {
        auctionAPI = null;
        auctionLot1 = null;
        bidder1 = null;
    }

    @Test
    void addBidder() {
        assertEquals(bidder1, auctionAPI.getBidders().get(0));
        assertEquals(bidder1, auctionAPI.getBidderHashTable().get(Integer.parseInt(bidder1.getPhone())));
    }

    @Test
    void addAuctionLot() {
        auctionAPI.addAuctionLot(auctionLot1);
        assertEquals(auctionLot1, auctionAPI.getUnsoldItems().get(0));
    }

    @Test
    void removeBidder() {
        assertEquals(bidder1, auctionAPI.getBidders().get(0));
        assertEquals(bidder1, auctionAPI.getBidderHashTable().get(Integer.parseInt(bidder1.getPhone())));
        auctionAPI.removeBidder(bidder1);
        assertEquals(bidder2, auctionAPI.getBidders().get(0));
        assertNull(auctionAPI.getBidderHashTable().get(Integer.parseInt(bidder1.getPhone())));
    }

    @Test
    void updateHashTable() {
        assertEquals(bidder1, auctionAPI.getBidderHashTable().get(Integer.parseInt(bidder1.getPhone())));
        auctionAPI.updateHashTable(bidder1, "5812320", "25490");
        assertNull(auctionAPI.getBidderHashTable().get(5812320));
        assertEquals(bidder1, auctionAPI.getBidderHashTable().get(25490));
    }

    @Test
    void removeAuctionLot() {
        assertEquals(auctionLot1, auctionAPI.getUnsoldItems().get(0));
        auctionAPI.removeAuctionLot(auctionLot1);
        assertNull(auctionAPI.getUnsoldItems().get(0));
    }

    @Test
    void findBidder() {
        assertEquals(bidder1, auctionAPI.getBidderHashTable().get(Integer.parseInt(bidder1.getPhone())));
        assertEquals(bidder2, auctionAPI.getBidderHashTable().get(Integer.parseInt(bidder2.getPhone())));
    }
}