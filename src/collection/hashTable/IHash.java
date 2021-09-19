package collection.hashTable;

public interface IHash<K,V> {
    public void addElement(K key,V value);
    public void remove(K key);
    public boolean isFull();
    public boolean isEmpty();
    public V search(K key);
    public int getSize();
    public int getIndexInTable(K key);
    public V getRack(int i);
}
