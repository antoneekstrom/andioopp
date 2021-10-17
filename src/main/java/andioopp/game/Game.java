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
 * @author Anton Ekström
 * @param <M>
 */
public abstract class Game<M> {
    private final ListFactory listFactory;

    private final M model;
    private final List<System<M>> systems;

    private final List<Controller<M>> controllers;
    private final List<View<M>> views;

    private Window<?> window;

    public Game(ListFactory listFactory, WindowBuilder<? extends Window<?>> windowBuilder) {
        this.listFactory = listFactory;
        this.window = initWindow(windowBuilder);
        this.model = initModel();
        this.systems = initSystems();
        this.views = initViews();
        this.controllers = initControllers();
    }

    public void init() {
        for (Controller<M> controller : controllers) {
            controller.init(model, window);
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

    public void render(Time time) {
        window.getRenderer().clear(Color.WHITE);
        for (View<M> view : views) {
            view.render(model, window.getRenderer());
        }
    }

    protected abstract Window<?> initWindow(WindowBuilder<? extends Window<?>> windowBuilder);
    protected abstract M initModel();
    protected abstract List<System<M>> initSystems();
    protected abstract List<Controller<M>> initControllers();
    protected abstract List<View<M>> initViews();

    protected M getModel() {
        return model;
    }

    protected Window<?> getWindow() {
        return window;
    }

    protected ListFactory getListFactory() {
        return listFactory;
    }
}
