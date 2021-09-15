package collection.hashTable;

public class Node<K, V> {
    private K key;
    private V value;
    private Node<K, V> next;
    private Node<K, V> prev;

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

    public Node<K, V> getNext() {
        return this.next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public Node<K, V> getPrev() {
        return this.prev;
    }

    public void setPrev(Node<K, V> prev) {
        this.prev = prev;
    }

}
