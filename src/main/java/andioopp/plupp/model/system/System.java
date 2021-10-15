package andioopp.plupp.model.system;

import andioopp.common.time.Time;

/**
 * Represents a system which performs some logic on the model.
 *
 * @param <M> the type of the model
 * @author Anton Ekström, Jacob Bengtsson
 */
@FunctionalInterface
public interface System<M> {

    /**
     * Applies the system on the model.
     *
     * @param model the model
     * @param time  which moment in time to update
     */
    void update(M model, Time time);

}
