package andioopp.control;

import andioopp.common.storage.ListFactory;
import andioopp.common.transform.Rectangle;
import andioopp.model.Model;
import andioopp.model.world.World;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.View;

import java.util.Collection;

public class PlaceTowerController {

    private final Model model;
    private final View<?> view;
    private final DragAndDropService<TowerDragEvent> dragAndDropService;
    private final Collection<CellDroppableController> droppables;

    public PlaceTowerController(DragAndDropService<TowerDragEvent> dragAndDropService, Model model, View<?> view, ListFactory listFactory) {
        this.model = model;
        this.dragAndDropService = dragAndDropService;
        this.view = view;
        droppables = listFactory.create();
    }

    private void registerDroppableCells() {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                Rectangle rectangle = view.getCellRectangle(world, row, col);
                CellDroppableController droppable = new CellDroppableController(rectangle, model, row, col);
                dragAndDropService.getDroppableObservable().addObserver(droppable);
                droppables.add(droppable);
            }
        }
    }

    public void register() {
        dragAndDropService.register();
        registerDroppableCells();
    }

    private void unregisterDroppableCells() {
        droppables.forEach(d -> dragAndDropService.getDroppableObservable().removeObserver(d));
    }

    public void unregister() {
        dragAndDropService.unregister();
        unregisterDroppableCells();
    }
}
