package andioopp.controller.controllers;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.controller.service.input.Draggable;
import andioopp.controller.service.input.MouseEvent;
import andioopp.model.domain.player.TowerCard;

public class TowerCardDraggableController extends Draggable<TowerCardDragEvent> {

    private final TowerCard<?> card;

    public TowerCardDraggableController(Rectangle rectangle, TowerCard<?> card) {
        super(rectangle);
        this.card = card;
    }

    @Override
    protected TowerCardDragEvent getDragData() {
        return new TowerCardDragEvent(card);
    }

    @Override
    public void onEvent(MouseEvent event) {
    }
}
