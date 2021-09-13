package andioopp.common.observer;

@FunctionalInterface
public interface Observer<T> {
    void onEvent(T event);
}
