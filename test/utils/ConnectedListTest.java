package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Random;

class ConnectedListTest {
    public ConnectedList<String> list = new ConnectedList<>();

    @BeforeEach
    void setUp() {
//        for (int i = 0; i <= 10000; i++) {
//            list.add(getAlphaNumericString(6));
//        }

        list.add("hello");
        list.add("Hi");
        list.add("goodBye");
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    void mergeSortTest() {
        list.mergeSort(Comparator.comparing(String::toString));
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}