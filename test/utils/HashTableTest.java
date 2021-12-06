package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable<String> hashTable = new HashTable<String>(50);

    @BeforeEach
    void setUp() {
        for(int i = 0; i <= 10; i++){
            hashTable.add(i, "Hi" + i);
        }
    }

    @AfterEach
    void tearDown() {
        hashTable = null;
    }

    @Test
    void add() {
        System.out.println(hashTable.get(11));
        assertNull(hashTable.get(11));
        hashTable.add(11, "Hi 11");
        System.out.println(hashTable.get(11));
        assertEquals(hashTable.get(11), "Hi 11");
    }

    @Test
    void get() {
    }
}