package mobile.search;

public interface Filter<T> {
    boolean matches(T item);
}
