package andioopp.controller.controllers;

import andioopp.common.graphics.Window;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.storage.ListFactory;
import andioopp.controller.Controller;
import andioopp.model.Model;
import andioopp.model.domain.player.TowerCard;
import andioopp.controller.input.DragAndDrop;
import andioopp.model.domain.world.World;
import andioopp.view.views.gui.CardsView;
import andioopp.view.views.world.LanesView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlaceTowerController implements Controller<Model> {

    private Model model;
    private Collection<CellDroppableController> droppables;
    private Collection<TowerCardDraggableController> draggables;

    private final DragAndDrop<TowerCardDragEvent> dragAndDrop;
    private final CardsView cardsView;
    private final LanesView lanesView;

    public PlaceTowerController(DragAndDrop<TowerCardDragEvent> dragAndDrop, CardsView cardsView, LanesView lanesView) {
        this.dragAndDrop = dragAndDrop;
        this.cardsView = cardsView;
        this.lanesView = lanesView;
    }

    public void init(Model model, Window<?> window) {
        ListFactory listFactory = ArrayList::new;
        this.model = model;
        this.droppables = listFactory.create();
        this.draggables = listFactory.create();
        registerDroppableCells();
        registerDraggableCards();
    }

    public void deinit() {
        unregisterDroppableCells();
        unregisterDraggableCells();
    }

    private void unregisterDroppableCells() {
        droppables.forEach(d -> dragAndDrop.getDroppableObservable().removeObserver(d));
        droppables.clear();
    }

    private void unregisterDraggableCells() {
        draggables.forEach(d -> dragAndDrop.getDraggableObservable().removeObserver(d));
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
            addDraggable(createCardDraggable(cards.get(i), i));
        }
    }

    private void addDroppable(CellDroppableController droppable) {
        dragAndDrop.getDroppableObservable().addObserver(droppable);
        droppables.add(droppable);
    }

    private void addDraggable(TowerCardDraggableController draggable) {
        dragAndDrop.getDraggableObservable().addObserver(draggable);
        draggables.add(draggable);
    }

    private CellDroppableController createCellDroppable(int row, int col) {
        Vector3f position = lanesView.getCellPosition(col, row);
        Dimension size = lanesView.getCellSize();
        Rectangle rectangle = new ImmutableRectangle(position, size);
        return new CellDroppableController(rectangle, model, row, col);
    }

    private TowerCardDraggableController createCardDraggable(TowerCard<?> card, int i) {
        Rectangle rectangle = cardsView.getCardView(card, i).getCardViewRectangle();
        return new TowerCardDraggableController(rectangle, card);
    }
}
