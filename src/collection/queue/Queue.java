package collection.queue;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> implements IQueue<T>, Cloneable {

    private Node<T> front;

    public Queue() {
    }

    @Override
    public void enqueue(T newItem) {
        if (front == null) {
            front = new Node<T>(newItem);
        } else {
            getLast(front).setBack(new Node<T>(newItem));
        }
    }

    private Node<T> getLast(Node<T> current) {
        if (current.getBack() == null) {
            return current;
        } else {
            return getLast(current.getBack());
        }
    }

    @Override
    public boolean isEmpty() {
        return (front == null);
    }

    @Override
    public T front() {
        return front.getData();
    }

    @Override
    public T dequeue() {
        T aux;
        if (front == null) {
            aux = null;
        } else if (front.getBack() != null) {
            aux = front.getData();
            front = front.getBack();
        } else {
            aux = front.getData();
            front = null;
        }
        return aux;
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> aux = front;
        while (aux != null) {
            count++;
            aux = aux.getBack();
        }
        return count;
    }

    @Override
    public List<T> convertQueToArr(Queue<T> line) {
        List<T> temp = new ArrayList<>();
        Queue<T> clone = line.clone().getClass();
        while(!line.isEmpty()){
            temp.add(line.dequeue());
        }
        return temp;
    }

    @Override
    public T getLast() {
        return getLast(front).getData();
    }

}
