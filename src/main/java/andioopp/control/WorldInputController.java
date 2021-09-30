package andioopp.control;

import andioopp.common.input.Clickable;
import andioopp.common.input.MouseData;
import andioopp.common.observer.Observable;
import andioopp.common.observer.ObservableDelegate;
import andioopp.common.storage.ListFactory;
import andioopp.model.world.World;
import andioopp.service.infrastructure.input.MouseInputService;
import andioopp.view.View;

public class WorldInputController {

    private final World world;
    private final View<?> view;
    private final Observable<MouseData> mouseDataObservableDelegate;

    public WorldInputController(World world, View<?> view, MouseInputService mouseInputService, ListFactory listFactory) {
        this.world = world;
        this.view = view;
        mouseDataObservableDelegate = new ObservableDelegate<>(mouseInputService.getMouseDataObservable(), listFactory);
    }

    public void register() {
        view.getCellClickables(world).forEach(this::addClickable);
    }

    public void unregister() {
        mouseDataObservableDelegate.getObservers().clear();
    }

    private void addClickable(Clickable clickable) {
        mouseDataObservableDelegate.addObserver(null);
    }
}
