package andioopp.common.observer;

import java.util.Collection;

public interface Observable<T> {
    Collection<Observer<T>> getObservers();
    default void notifyObservers(T event) {
        getObservers().forEach(observer -> observer.onEvent(event));
    }
    default void addObserver(Observer<T> observer) {
        getObservers().add(observer);
    }
    default void removeObserver(Observer<T> observer) {
        getObservers().remove(observer);
    }
}
