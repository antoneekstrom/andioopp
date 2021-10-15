package andioopp.controller;

import andioopp.common.graphics.Window;
import andioopp.controller.service.ServiceProvider;
import andioopp.view.View;

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
     * @param model           the model
     * @param view            the view
     * @param serviceProvider the services
     */
    void init(M model, Window<?> window, ServiceProvider serviceProvider);

    /**
     * Deinitializes the controller.
     */
    void deinit();

}
