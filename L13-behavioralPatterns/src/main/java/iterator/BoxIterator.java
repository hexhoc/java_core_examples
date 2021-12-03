package iterator;

public interface BoxIterator<T> {
    boolean hasNext();
    T next();
    void reset();
}
