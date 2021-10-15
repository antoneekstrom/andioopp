package andioopp.game;

import andioopp.common.graphics.Window;
import andioopp.common.time.Time;
import andioopp.controller.Controller;
import andioopp.controller.service.ServiceProvider;
import andioopp.model.Model;
import andioopp.model.system.System;
import andioopp.view.View;

import java.util.List;

/**
 * @author Anton Ekström
 * @param <M>
 */
public abstract class Game<M> {
    private final M model;
    private final List<Controller<M>> controllers;
    private final List<View<M>> views;
    private final List<System<M>> systems;
    private final ServiceProvider services;
    private final Window<?> window;

    public Game(ServiceProvider services, Window<?> window) {
        this.window = window;
        this.services = services;
        this.systems = initSystems();
        this.model = initModel();
        this.views = initViews();
        this.controllers = initControllers();
    }

    public void init() {
        for (Controller<M> controller : controllers) {
            controller.init(model, window, services);
        }
    }

    public void deinit() {
        for (Controller<M> controller : controllers) {
            controller.deinit();
        }
    }

    public void update(Time time) {
        for (System<M> system : systems) {
            system.update(model, time);
        }
    }

    protected ServiceProvider getServices() {
        return services;
    }

    protected abstract M initModel();
    protected abstract List<System<M>> initSystems();
    protected abstract List<Controller<M>> initControllers();
    protected abstract List<View<M>> initViews();
}
