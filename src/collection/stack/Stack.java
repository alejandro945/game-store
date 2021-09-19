package collection.stack;

import java.io.Serializable;
import java.util.List;

public class Stack<T> implements IStack<T>, Serializable {

    private Node<T> top;

    public Stack() {
    }

    @Override
    public void push(T newItem) {
        if (top == null) {
            top = new Node<T>(newItem);
        } else {
            Node<T> aux = top;
            top = new Node<T>(newItem);
            top.setNext(aux);
        }

    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    @Override
    public T top() {
        return top.getData();
    }

    @Override
    public T pop() {
        T aux;
        if (top == null) {
            aux = null;
        } else if (top.getNext() != null) {
            aux = top.getData();
            top = top.getNext();
        } else {
            aux = top.getData();
            top = null;
        }
        return aux;
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> aux = top;
        while (aux != null) {
            count++;
            aux = aux.getNext();
        }
        return count;
    }

    @Override
    public void convertArrtoStack(List<T> list) {
        top = null;
        for (int i = 0; i<list.size(); i++){
            push(list.get(i));
        }
    }

}
