package collection.stack;

public interface IStack<T> {

    public void push(T newItem);

    public boolean isEmpty();

    public T top();

    public T pop();

    public int size();
}
