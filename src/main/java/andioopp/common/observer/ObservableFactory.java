package andioopp.common.observer;

public interface ObservableFactory {
    <T> Observable<T> create();
}
