package andioopp.control;

import andioopp.common.transform.Rectangle;
import andioopp.model.Model;
import andioopp.service.infrastructure.input.DragAndDropObserver;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.View;

public class PlaceTowerController {

    private final Model model;
    private final View<?> view;
    private final DragAndDropService dragAndDropService;

    public PlaceTowerController(DragAndDropService dragAndDropService, Model model, View<?> view) {
        this.model = model;
        this.dragAndDropService = dragAndDropService;
        this.view = view;
    }

    private void registerDraggableCells() {
        for (Rectangle rectangle : view.getCellRectangles(model.getWorld())) {
            dragAndDropService.getDroppableObservable().addObserver(new DragAndDropObserver(rectangle));
        }
    }

    public void register() {
        dragAndDropService.register();
        registerDraggableCells();
    }

    public void unregister() {
        dragAndDropService.unregister();
    }

}
