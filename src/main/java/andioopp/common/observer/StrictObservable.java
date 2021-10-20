package andioopp.common.observer;

import java.util.Collection;

public interface StrictObservable<T, O extends Observer<T>> {

    /**
     * Returns the collection of observers. This is the internal collection of observers.
     * Therefore, one should be careful when mutating this collection.
     * @return the observers
     */
    Collection<O> getObservers();

    /**
     * Emits an event to all observers.
     * @param event the event to emit
     */
    default void notifyObservers(T event) {
        getObservers().forEach(observer -> observer.onEvent(event));
    }

    /**
     * Registers an observer to this observable.
     * @param observer the observer to register
     */
    default void addObserver(O observer) {
        getObservers().add(observer);
    }

    /**
     * Removes an observer from this observable, if it has already been registered.
     * @param observer the observer to remove
     */
    default void removeObserver(O observer) {
        getObservers().remove(observer);
    }

}
