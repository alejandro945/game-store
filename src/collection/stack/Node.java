package collection.stack;

import java.io.Serializable;

public class Node<T> implements Serializable {

    private Node<T> next;
    private T data;

    public Node(T information) {
        data = information;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
