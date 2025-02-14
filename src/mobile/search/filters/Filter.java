package mobile.search.filters;

public interface Filter<T> {
    boolean matches(T item);
}
