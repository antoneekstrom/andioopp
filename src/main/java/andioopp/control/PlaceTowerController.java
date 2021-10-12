package andioopp.control;

import andioopp.common.storage.ListFactory;
import andioopp.common.transform.Rectangle;
import andioopp.model.Model;
import andioopp.model.tower.Tower;
import andioopp.model.world.World;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.LanesView;
import andioopp.view.gui.CardsView;
import andioopp.view.gui.TowerCard;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class PlaceTowerController {

    private final Model model;
    private final DragAndDropService<TowerDragEvent> dragAndDropService;
    private final Collection<CellDroppableController> droppables;
    private final Collection<TowerCardDraggableController> draggables;

    public PlaceTowerController(DragAndDropService<TowerDragEvent> dragAndDropService, Model model, ListFactory listFactory) {
        this.model = model;
        this.dragAndDropService = dragAndDropService;
        droppables = listFactory.create();
        draggables = listFactory.create();
    }

    public void register(LanesView<?> lanesView, CardsView<?> towerCardsView) {
        dragAndDropService.register();
        registerDroppableCells(lanesView);
        registerDraggableCards(model, towerCardsView);
    }

    public void unregister() {
        dragAndDropService.unregister();
        unregisterDroppableCells();
        unregisterDraggableCells();
    }

    private void registerDroppableCells(LanesView<?> lanesView) {
        World world = model.getWorld();

        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                CellDroppableController droppable = createCellDroppable(lanesView, world, row, col);
                addDroppable(droppable);
            }
        }
    }

    private void registerDraggableCards(Model model, CardsView<?> cardsView) {
        List<Supplier<Tower>> cards = model.getCards();
        for (int i = 0; i < cards.size(); i++) {
            TowerCardDraggableController draggable = createCardDraggable(cardsView, cards, i);
            addDraggable(draggable);
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

    private CellDroppableController createCellDroppable(LanesView<?> lanesView, World world, int row, int col) {
        Rectangle rectangle = lanesView.getCellRect(world, row, col);
        return new CellDroppableController(rectangle, model, row, col);
    }

    private TowerCardDraggableController createCardDraggable(CardsView<?> cardsView, List<Supplier<Tower>> cards, int i) {
        TowerCard<?> card = new TowerCard<>(cards.get(i));
        Rectangle rectangle = cardsView.getTowerCardRect(i);
        return new TowerCardDraggableController(rectangle, card.getTowerSupplier());
    }

    private void unregisterDroppableCells() {
        droppables.forEach(d -> dragAndDropService.getDroppableObservable().removeObserver(d));
        droppables.clear();
    }

    private void unregisterDraggableCells() {
        draggables.forEach(d -> dragAndDropService.getDraggableObservable().removeObserver(d));
        draggables.clear();
    }
}
