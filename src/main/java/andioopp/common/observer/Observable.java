package andioopp.common.observer;

/**
 * Represents an observable in the "observer pattern".
 * An observable can be listened to by observers and broadcast events to its listeners.
 *
 * @param <T> type of the event
 * @author Anton Ekström
 * @see Observer
 */
@FunctionalInterface
public interface Observable<T> extends StrictObservable<T, Observer<T>> {
}
