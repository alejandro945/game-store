package collection.hashTable;

import java.io.Serializable;

public class Node<K, V> implements Serializable {
    private K key;
    private V value;

    public Node(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
