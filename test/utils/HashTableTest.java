package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable<String> hashTable = new HashTable<>(50);

    @BeforeEach
    void setUp() {
        for(int i = 0; i <= 50; i++){
            hashTable.add(i, "Hi " + i);
        }
    }

    @AfterEach
    void tearDown() {
        hashTable = null;
    }

    @Test
    void add() {
        assertNull(hashTable.get(51));
        hashTable.add(51, "Hi 51");
        assertEquals(hashTable.get(51), "Hi 51");
    }

    @Test
    void remove() {
        assertNotNull(hashTable.get(6));
        assertEquals(hashTable.get(6), "Hi 6");
        hashTable.remove(6, "Hi 6");
        assertNull(hashTable.get(6));
    }

    @Test
    void get() {
        for(int i = 0; i <= 10; i++) {
            assertEquals(hashTable.get(i), "Hi " + i);
        }
    }

    @Test
    void rehash(){
        hashTable.add(51, "Hi 51");
        assertEquals("Hi 51",hashTable.get(51));
    }

    @Test
    void contains(){
        assertFalse(hashTable.contains("Hi 51"));
        hashTable.add(11, "Hi 51");
        assertTrue(hashTable.contains("Hi 51"));
    }

    @Test
    void listAll(){
        for (int i = 0; i <= 10; i++) {
            assertTrue(hashTable.listAll().contains("Hi " + i));
        }
    }
}