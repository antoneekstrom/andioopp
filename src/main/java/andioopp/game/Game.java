package andioopp.game;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.Window;
import andioopp.common.storage.ListFactory;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.interfaces.Updateable;
import andioopp.service.Service;
import andioopp.service.domain.DomainService;
import andioopp.view.View;

import java.util.Collection;

/**
 * Encapsulates neccessary components for creating a game.
 * Handles updating and initializing services and views.
 *
 * @author Anton Ekström
 * @param <S> type of sprite in views
 */
public class Game<S extends Sprite<?>> implements Updateable {

    private final Model model;
    private final Window<? extends Renderer<S>> window;

    private final Collection<View<S>> views;
    private final Collection<Service<Game<?>>> services;

    public Game(Model model, Window<? extends Renderer<S>> window, ListFactory listFactory) {
        this.model = model;
        this.window = window;
        this.views = listFactory.create();
        this.services = listFactory.create();
    }

    /**
     * Invokes {@link Service#onSetup(Object)} on services.
     */
    public void setup() {
        for (Service<Game<?>> service : services) {
            service.onSetup(this);
        }
    }

    /**
     * Invokes {@link Service#onDestroy(Object)} on services.
     */
    public void destroy() {
        for (Service<Game<?>> service : services) {
            service.onDestroy(this);
        }
    }

    /**
     * Returns the window.
     * @return the window
     */
    public Window<? extends Renderer<S>> getWindow() {
        return window;
    }

    /**
     * Returns the model.
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    @Override
    public void update(Time time) {
        for (Service<Game<?>> service : services) {
            updateService(service, time);
        }
        for (View<S> view : views) {
            renderView(view);
        }
    }

    private void updateService(Service<Game<?>> service, Time time) {
        service.update(this, time);
    }

    private void renderView(View<S> view) {
        view.render(getWindow().getRenderer(), getModel());
    }

    public void registerView(View<S> view) {
        views.add(view);
    }

    public void registerDomainService(DomainService domainService) {
        services.add(new GameServiceAdapter<>(domainService, this::domainServiceAdapter));
    }

    private Model domainServiceAdapter(Game<?> game) {
        return game.getModel();
    }
}
