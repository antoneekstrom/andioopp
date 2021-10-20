package andioopp.controller;

import andioopp.common.graphics.Window;

/**
 * Handles and translates user input from the view into actions on the model.
 *
 * @param <M> the type of the model
 * @author Anton Ekström, Jacob Bengtsson
 */
public interface Controller<M> {

    /**
     * Initializes the controller and provides it with dependencies.
     *
     * @param model  the model
     * @param window the view
     */
    void init(M model, Window<?> window);

    /**
     * Deinitializes the controller.
     */
    void deinit(M model, Window<?> window);

}
