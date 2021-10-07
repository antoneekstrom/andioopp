package andioopp.common.time;

import andioopp.common.observer.Observer;

/**
 * Repeatedly emits events.
 */
public interface Clock {

    /**
     * Starts the clock so that it emits events.
     */
    void start();

    /**
     * Stops the clock so that it stops emitting events.
     */
    void stop();

    /**
     * Registers an observer to the clock.
     * @param listener the observer
     */
    void listen(Observer<Time> listener);

    /**
     * Unregisters an observer from the clock.
     * @param listener the observer
     */
    void unlisten(Observer<Time> listener);

    /**
     * Unregisters all observers from the clock.
     */
    void unlistenAll();
}
