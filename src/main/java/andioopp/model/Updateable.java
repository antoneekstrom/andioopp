package andioopp.model;

import andioopp.common.time.Time;

/**
 * Represents an object which updates its state based on time events emitted by a {@link andioopp.common.time.Clock}.
 */
@FunctionalInterface
public interface Updateable {

    /**
     * Updates based on time.
     * @param time the time
     */
    void update(Time time);

}
