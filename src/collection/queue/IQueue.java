package collection.queue;

import model.Costumer;

import java.util.ArrayList;
import java.util.List;

public interface IQueue<T> {
    public void enqueue(T newItem);
    public boolean isEmpty();
    public T front();
    public T getLast();
    public T dequeue();
    public int size();
    public List<T> convertQueToArr(Queue<T> line);
}
