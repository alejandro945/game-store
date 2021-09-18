package collection.hashTable;

import java.io.Serializable;

public class Hash<K, V> implements IHash<K, V>, Serializable {
    private Node<K, V>[] racks;
    private int currentSize, maxSize;

    @SuppressWarnings("unchecked")
    public Hash(int size) {
        currentSize = 0;
        maxSize = size;
        racks = (Node<K, V>[]) (new Node[maxSize]);
    }

    public int getSize() {
        return currentSize;
    }

    @Override
    public void addElement(K key, V value) {
        int slot = hash(key);
        int i = 0;
        int count = 0;
        boolean added = false;
        while (i != slot && !added) {
            if (count == 0)
                i = slot;
            if (racks[i] == null) {
                racks[i] = new Node<K, V>(key, value);
                currentSize++;
                added = true;
                ;
            }
            count++;
            i = (i + 1) % maxSize;
        }
    }

    @Override
    public void remove(K key) {
        if (search(key) != null) {
            // Ecncontrar position de key para eliminar
            int i = hash(key);
            while (!key.equals(racks[i].getKey())){
                i = (i + 1) % maxSize;
            }     
            racks[i] = null;
            // rehash todas las keys
            for (i = (i + 1) % maxSize; racks[i] != null; i = (i + 1) % maxSize) {
                K tempK = racks[i].getKey();
                V tempV = racks[i].getValue();
                racks[i] = null;
                currentSize--;
                addElement(tempK, tempV);
            }
            currentSize--;
        }
    }

    @Override
    public boolean isFull() {
        return (currentSize == maxSize);
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public V search(K key) {
        V render = null;
        int i = hash(key);
        // Cuando no hay elemento en el slot se sale o cuando lo encuentra
        while (racks[i] != null || render == null) {
            if (racks[i].getKey().equals(key)) {
                render = racks[i].getValue();
            }
            i = (i + 1) % maxSize;
        }
        return render;
    }

    private int hash(K key) {
        return key.hashCode() % maxSize;
    }

}
