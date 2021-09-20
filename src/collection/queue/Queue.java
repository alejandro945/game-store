package collection.queue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Queue<T> implements IQueue<T>,Serializable {

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
    public synchronized boolean isEmpty() {
        return (front == null);
    }

    @Override
    public synchronized T front() {
        return front.getData();
    }

    @Override
    public synchronized T dequeue() {
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
    public List<T> convertQueueToArr() {
        Node<T> aux = front;
        List<T> temp = new ArrayList<>();
        while(aux != null){
            temp.add(aux.getData());
            aux = aux.getBack();
        }
        return temp;
    }

    @Override
    public void clear() {
        front = null;
    }


    @Override
    public T getLast() {
        return getLast(front).getData();
    }

    @Override
    public boolean search(T elem) {
        boolean found = false;
        Node<T> aux = front;
        while (aux != null && !found) {
            if(aux.getData().equals(elem)){
                found = true;
            }
            aux = aux.getBack();
        }
        return found;
    }

    

}
