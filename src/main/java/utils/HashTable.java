package utils;


public class HashTable<E> {
    E[] hashTable;
    int probeFactor;
    private int size;
    private int count;

    public HashTable(int size) {
        hashTable = (E[]) new Object[size];
        this.probeFactor = 50;
        this.size = size;
    }

    public HashTable(int size, int probeFactor) {
        hashTable = (E[]) new Object[size];
        this.probeFactor = probeFactor;
        this.size = size;
    }

    public boolean add(int key, E o) {
        if (count >= size()){
            rehash();
            add(key, o);
        } else {
            int loc = hashFunction(key), home = loc, probe = 1;

            while (hashTable[loc] != null && probe < probeFactor) {
                loc = (home + (probe * probe++)) % hashTable.length;
            }
            if (probe < probeFactor) {
                hashTable[loc] = o;
                count++;
                return true;
            } else return false;
        }
        return false;
    }

    public void remove(int key, E o){
        int loc = hashFunction(key), home = loc, probe = 1;
        while (!hashTable[loc].equals(o) && probe < probeFactor) {
            loc = (home + (probe * probe++)) % hashTable.length;
        }
        if (probe < probeFactor) {
            hashTable[loc] = null;
            count--;
        }
    }

    public E get(int key) {
        int index = hashFunction(key);
        return hashTable[index];
    }

    public void rehash() {
        E[] hashTableNew;
        hashTableNew = (E[]) new Object[((size*3)/2)];
        for(int i = 0; i < hashTable.length; i++){
            hashTableNew[i] = hashTable[i];
        }
        hashTable = hashTableNew;
        size =  ((size*3)/2);
    }

    public boolean contains(E o){
        boolean found = false;

        for(E object: hashTable){
            if (object != null) {
                if (object.equals(o)) {
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public String listAll(){
        String list = "";

        for(E object: hashTable){
            list = list + object;
        }

        return list;
    }

    public int size(){
        return size;
    }

    public int hashFunction(int key) {
        return Math.abs(key % hashTable.length);
    }
}


