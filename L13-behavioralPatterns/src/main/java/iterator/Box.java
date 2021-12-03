package iterator;

import java.util.ArrayList;
import java.util.List;

public class Box<T> implements BoxIterator {

    private List<T> content = new ArrayList<>();
    private int currentPosition;

    public void add(T data) {
        content.add(data);
    }

    @Override
    public boolean hasNext() {
        return currentPosition < content.size();
    }

    @Override
    public T next() {
        return content.get(currentPosition++);
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}
