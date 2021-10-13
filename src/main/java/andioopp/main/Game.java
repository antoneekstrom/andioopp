package andioopp.main;

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

    public void setup() {
        services.forEach((service) -> service.onSetup(this));
    }

    public void destroy() {
        services.forEach((service) -> service.onDestroy(this));
    }

    @Override
    public void update(Time time) {
        services.forEach((service) -> updateService(service, time));
        views.forEach(this::renderView);
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

    public Window<? extends Renderer<S>> getWindow() {
        return window;
    }

    public Model getModel() {
        return model;
    }
}
