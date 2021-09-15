package collection.queue;

public class Node<T> {

    private Node<T> back;
    private T data;

    public Node(T information) {
        data = information;
    }


    public Node<T> getBack() {
        return this.back;
    }

    public void setBack(Node<T> back) {
        this.back = back;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
