package andioopp.common.observer;

import java.util.Collection;

public interface Observable<T> {
    Collection<Observer<T>> getObservers();
    void notifyObservers(T event);
    void addObserver(Observer<T> observer);
    void removeObserver(Observer<T> observer);

}
