package andioopp.controller;

import andioopp.controller.service.ServiceProvider;
import andioopp.view.View;

/**
 * Handles and translates user input from the view into actions on the model.
 *
 * @param <M> the type of the model
 * @param <V> the type of the view
 * @author Anton Ekström, Jacob Bengtsson
 */
public interface Controller<M, V extends View<M>> {

    /**
     * Initializes the controller and provides it with dependencies.
     *
     * @param model           the model
     * @param view            the view
     * @param serviceProvider the services
     */
    void init(M model, V view, ServiceProvider serviceProvider);

    /**
     * Deinitializes the controller.
     */
    void deinit();

}
