package andioopp.common.observer;

import java.util.Collection;

public interface Observable<T, O extends Observer<T>> {
    Collection<O> getObservers();
    default void notifyObservers(T event) {
        getObservers().forEach(observer -> observer.onEvent(event));
    }
    default void addObserver(O observer) {
        getObservers().add(observer);
    }
    default void removeObserver(O observer) {
        getObservers().remove(observer);
    }
}
