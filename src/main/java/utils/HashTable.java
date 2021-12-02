package utils;

import java.util.LinkedList;
import java.util.List;

public class HashTable<E> {
    E[] hashTable;
    int probeFactor;

    public HashTable(int size) {
        hashTable = (E[]) new Object[size];
        this.probeFactor = 50;
    }

    public HashTable(int size, int probeFactor) {
        hashTable = (E[]) new Object[size];
        this.probeFactor = probeFactor;
    }

    public boolean add(E o) {
        int loc = hashFunction(o), home = loc, probe = 1;

        while (hashTable[loc] != null && probe < probeFactor) {
            loc = (home + (probe * probe++)) % hashTable.length;
        }
        if (probe < probeFactor) {
            hashTable[loc] = o;
            return true;
        } else return false;
    }

    public E get(){
        return null;
    }

    public int hashFunction(E o) {
        String string = o.toString();
        int total = 0;
        for (int i = 0; i < string.length(); ++i) {
            total = string.charAt(i);
        }
        return total % hashTable.length;
    }
}
