package andioopp.game;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Window;
import andioopp.common.graphics.WindowBuilder;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.controller.Controller;
import andioopp.model.system.System;
import andioopp.view.View;

import java.util.List;

/**
 * Encapsulates neccessary components for running a game.
 *
 * @param <M> the type of the model
 * @author Anton Ekström
 * @see Controller
 * @see View
 * @see System
 * @see Window
 */
public abstract class Game<M> {
    private final WindowBuilder<? extends Window<?>> windowBuilder;
    private final ListFactory listFactory;

    private Window<?> window;

    private M model;
    private List<System<M>> systems;
    private List<Controller<M>> controllers;
    private List<View<M>> views;

    /**
     * Creates a game.
     *
     * @param listFactory the listfactory for creating lists
     * @param windowBuilder the window builder for building the window
     */
    public Game(ListFactory listFactory, WindowBuilder<? extends Window<?>> windowBuilder) {
        this.listFactory = listFactory;
        this.windowBuilder = windowBuilder;
    }

    /**
     * Initializes the game in the following order:
     * - Creates the window
     * - Creates the model
     * - Initializes the systems
     * - Initializes the views
     * - Initializes the controllers
     * - Initializes the services
     */
    public void init() {
        this.window = initWindow(windowBuilder);
        this.model = initModel();
        this.systems = initSystems();
        this.views = initViews();
        this.controllers = initControllers();
        initServices();

        for (Controller<M> controller : controllers) {
            controller.init(model, window);
        }
    }

    /**
     * Deinitializes the game.
     */
    public void deinit() {
        for (Controller<M> controller : controllers) {
            controller.deinit(model, window);
        }
    }

    /**
     * Updates the game.
     *
     * @param time the time
     */
    public void update(Time time) {
        for (System<M> system : systems) {
            system.update(model, time);
        }
    }

    /**
     * Renders the game.
     *
     * @param time the time
     */
    public void render(Time time) {
        window.getRenderer().clear(Color.WHITE);
        for (View<M> view : views) {
            view.render(model, window.getRenderer());
        }
    }

    /**
     * Configures and creates the window using the given {@link WindowBuilder}.
     *
     * @param windowBuilder the window builder
     * @return the window
     */
    protected abstract Window<?> initWindow(WindowBuilder<? extends Window<?>> windowBuilder);

    /**
     * Creates the model.
     *
     * @return the model
     */
    protected abstract M initModel();

    /**
     * Creates and initializes the systems.
     *
     * @return the list of systems
     */
    protected abstract List<System<M>> initSystems();

    /**
     * Creates and initializes the views.
     *
     * @return the list of views
     */
    protected abstract List<View<M>> initViews();

    /**
     * Creates and initializes the controllers.
     *
     * @return the list of controllers
     */
    protected abstract List<Controller<M>> initControllers();

    /**
     * Initializes services.
     */
    protected abstract void initServices();

    /**
     * Returns the list of controllers.
     *
     * @return the controllers
     */
    protected List<Controller<M>> getControllers() {
        return controllers;
    }

    /**
     * Returns the list of views.
     *
     * @return the views
     */
    protected List<View<M>> getViews() {
        return views;
    }

    /**
     * Returns the model.
     *
     * @return the model
     */
    protected M getModel() {
        return model;
    }

    /**
     * Returns the window.
     *
     * @return the window
     */
    protected Window<?> getWindow() {
        return window;
    }

    /**
     * Returns the listfactory.
     *
     * @return the listfactory
     */
    protected ListFactory getListFactory() {
        return listFactory;
    }
}
