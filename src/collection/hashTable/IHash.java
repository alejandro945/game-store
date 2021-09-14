package collection.hashTable;

public interface IHash<Key,Value> {
    public String addValue(Key key,Value value);
    public String removeValue(Key key);
}
