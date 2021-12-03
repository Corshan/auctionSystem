package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedListTest {
    public ConnectedList<String> list = new ConnectedList<>();

    @BeforeEach
    void setUp() {
        for(int i = 0; i < 5; i++) {
            list.add("Hi " + i);
        }
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    void add() {
        assertEquals(5, list.size());
        list.add("Yeah");
        assertEquals(6, list.size());
    }

    @Test
    void addFromTop(){
        list.addFromTop("Yeah");
        assertEquals(list.get(0), "Yeah");
        assertEquals(list.get(1), "Hi 0");
    }

    @Test
    void removeByIndex() {
        assertEquals(5, list.size());
        list.remove(0);
        assertEquals(4, list.size());
    }

    @Test
    void removeByObject() {
        assertEquals(5, list.size());
        list.remove(list.get(0));
        assertEquals(4, list.size());
    }

    @Test
    void clear() {
        assertEquals(5, list.size());
        list.clear();
        assertEquals(0,list.size());
        assertFalse(list.contains("Hi 1"));
    }

    @Test
    void get() {
        assertEquals("Hi 3", list.get(3));
    }

    @Test
    void set() {
        assertEquals("Hi 3", list.get(3));
        list.set(3,"Yo");
        assertEquals("Yo",list.get(3));
    }

    @Test
    void size() {
        assertEquals(5, list.size());
        list.add("Yeah");
        list.add("Hello");
        list.add("Greetings");
        assertEquals(8, list.size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(list.contains("Hi 1"));
        list.add("Yeah");
        assertTrue(list.contains("Yeah"));
    }

    @Test
    void mergeSortTest() {
        list.clear();
        list.add("BB6666");
        for (int i = 0; i <= 10; i++) {
            list.add(getAlphaNumericString(6));
        }
        list.mergeSort(Comparator.comparing(String::toString));
        assertNotEquals(list.get(0),"BB6666");
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