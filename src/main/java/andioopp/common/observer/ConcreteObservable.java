package andioopp.common.observer;

import java.util.List;

/**
 * An observable which uses a list to store its observers.
 * @param <T> type of the event
 * @param <O> type of the observer
 */
public class ConcreteObservable<T, O  extends Observer<T>> implements Observable<T, O> {
    private final List<O> observers;
    public ConcreteObservable(List<O> observers) {
        this.observers = observers;
    }
    public List<O> getObservers() {
        return observers;
    }
}
