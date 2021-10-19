package andioopp.common.time;

import andioopp.common.observer.Observable;
import andioopp.common.observer.Observer;

/**
 * Repeatedly emits events.
 */
public interface Clock extends Observable<Time, Observer<Time>> {

    /**
     * Starts the clock so that it emits events.
     */
    void start();

    /**
     * Stops the clock so that it stops emitting events.
     */
    void stop();
}
