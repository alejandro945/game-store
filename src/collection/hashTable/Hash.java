package collection.hashTable;

import java.io.Serializable;

public class Hash<K, V> implements IHash<K, V>,Serializable {
    private int size;

    public Hash(int size) {
        this.size = size;
    }

    @Override
    public String addValue(K key, V value) {
        return null;
    }

    @Override
    public String removeValue(K key) {
        return null;
    }

}
