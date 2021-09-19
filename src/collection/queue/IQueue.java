package collection.queue;
import java.util.List;

public interface IQueue<T> {
    public void enqueue(T newItem);
    public boolean isEmpty();
    public T front();
    public T getLast();
    public T dequeue();
    public int size();
    public boolean search(T elem);
    public List<T> convertQueueToArr();
    public void convertArrtoQueue(List<T> list);
}
