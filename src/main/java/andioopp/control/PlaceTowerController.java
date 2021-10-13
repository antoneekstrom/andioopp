package andioopp.control;

import andioopp.common.storage.ListFactory;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.model.Model;
import andioopp.model.player.TowerCard;
import andioopp.model.world.World;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.views.LanesView;
import andioopp.view.views.CardsView;
import andioopp.view.views.TowerCardView;

import java.util.Collection;
import java.util.List;

public class PlaceTowerController {

    private final Model model;
    private final DragAndDropService<TowerCardDragEvent> dragAndDropService;
    private final Collection<CellDroppableController> droppables;
    private final Collection<TowerCardDraggableController> draggables;

    public PlaceTowerController(DragAndDropService<TowerCardDragEvent> dragAndDropService, Model model, ListFactory listFactory) {
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
        List<TowerCard<?>> cards = model.getPlayer().getCards();
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

    private TowerCardDraggableController createCardDraggable(CardsView<?> cardsView, List<TowerCard<?>> cards, int i) {
        TowerCardView<?> card = new TowerCardView<>(cards.get(i));
        Rectangle rectangle = cardsView.getTowerCardRect(i);
        return new TowerCardDraggableController(rectangle, card.getCard());
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
