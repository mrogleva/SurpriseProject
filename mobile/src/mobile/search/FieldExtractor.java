package mobile.search;

public interface FieldExtractor<T, V> {
    V extractValue(T item);
}
