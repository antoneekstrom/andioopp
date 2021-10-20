package andioopp.common.observer;

/**
 * Represents an observer in the "observer pattern".
 * An observer can listen to an observable to receive events.
 *
 * @param <T> the type of event
 * @author Anton Ekström
 * @see Observable
 */
@FunctionalInterface
public interface Observer<T> {
    /**
     * Called by the {@link Observable} when receiving an event.
     *
     * @param event the event sent by the observable
     */
    void onEvent(T event);
}
