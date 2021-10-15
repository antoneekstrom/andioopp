package andioopp.controller.controllers;

import andioopp.controller.service.ServiceProvider;
import andioopp.model.Model;
import andioopp.model.domain.player.TowerCard;
import andioopp.controller.service.input.DragAndDropService;
import andioopp.model.domain.world.World;
import andioopp.view.util.ModelViewport;
import andioopp.view.views.gui.TowerCardView;

import java.util.Collection;
import java.util.List;

public class PlaceTowerController {

    private Model model;
    private ModelViewport viewport;
    private DragAndDropService<TowerCardDragEvent> dragAndDropService;
    private Collection<CellDroppableController> droppables;
    private Collection<TowerCardDraggableController> draggables;

    public void init(Model model, ModelViewport viewport, ServiceProvider serviceProvider) {
        this.viewport = viewport;
        this.model = model;
        dragAndDropService = new DragAndDropService<>(serviceProvider.mouseService(), serviceProvider.getListFactory());
        registerDroppableCells();
        registerDraggableCards();
    }

    public void deinit() {
        unregisterDroppableCells();
        unregisterDraggableCells();
    }

    private void unregisterDroppableCells() {
        droppables.forEach(d -> dragAndDropService.getDroppableObservable().removeObserver(d));
        droppables.clear();
    }

    private void unregisterDraggableCells() {
        draggables.forEach(d -> dragAndDropService.getDraggableObservable().removeObserver(d));
        draggables.clear();
    }

    private void registerDroppableCells() {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                addDroppable(createCellDroppable(row, col));
            }
        }
    }

    private void registerDraggableCards() {
        List<TowerCard<?>> cards = model.getPlayer().getCards();
        for (int i = 0; i < cards.size(); i++) {
            addDraggable(createCardDraggable(cards, i));
        }
    }

    private void addDroppable(CellDroppableController droppable) {
        dragAndDropService.getDroppableObservable().addObserver(droppable);
        droppables.add(droppable);
    }

    private void addDraggable(TowerCardDraggableController draggable) {
        dragAndDropService.getDraggableObservable().addObserver(draggable);
        draggables.add(draggable);
    }

    private CellDroppableController createCellDroppable(int row, int col) {
        Rectangle cell = null;
        return new CellDroppableController(cell, model, row, col);
    }

    private TowerCardDraggableController createCardDraggable(List<TowerCard<?>> cards, int i) {
        Rectangle rectangle = null;
        TowerCardView<?> card = new TowerCardView<>(cards.get(i));
        return new TowerCardDraggableController(rectangle, card.getCard());
    }
}
