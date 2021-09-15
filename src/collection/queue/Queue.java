package collection.queue;

public class Queue<T> implements IQueue<T> {

    private Node<T> back;

    public Queue() {
    }

    @Override
    public void enqueue(T newItem) {
        

    }

    @Override
    public boolean isEmpty() {
        return (back == null);
    }

    @Override
    public T front() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T dequeue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> aux = back;
        while (aux != null) {
            count++;
            aux = aux.getBack();
        }
        return count;
    }

    @Override
    public T getLast() {
        // TODO Auto-generated method stub
        return null;
    }

}
