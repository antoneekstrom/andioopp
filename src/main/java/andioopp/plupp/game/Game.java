package andioopp.plupp.game;

import andioopp.plupp.controller.Controller;
import andioopp.plupp.service.ServiceProvider;
import andioopp.plupp.view.View;

import java.util.List;

/**
 * @author Anton Ekström
 * @param <M>
 */
public abstract class Game<M> {
    private final M model;
    private final List<Controller<M, ?>> controllers;
    private final List<View<M>> views;
    private final ServiceProvider services;

    public Game(ServiceProvider services) {
        this.services = services;
        this.model = initModel();
        this.views = initViews();
        this.controllers = initControllers();
    }

    protected ServiceProvider getServices() {
        return services;
    }

    protected abstract M initModel();
    protected abstract List<Controller<M, ?>> initControllers();
    protected abstract List<View<M>> initViews();
}
