package collection.queue;

public interface IQueue<T> {
    public void enqueue(T newItem);
    public boolean isEmpty();
    public T front();
    public T getLast();
    public T dequeue();
    public int size();
}
