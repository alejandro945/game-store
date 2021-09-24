package collection.stack;

import java.util.List;

public interface IStack<T> {
    public void push(T newItem);

    public boolean isEmpty();

    public T top();

    public T pop();

    public int size();

    public String getInfo();

    public void convertArrtoStack(List<T> list);
}
