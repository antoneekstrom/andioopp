package andioopp.controller.controllers;

import andioopp.common.math.rectangle.RectanglePlupp;
import andioopp.controller.service.input.Draggable;
import andioopp.controller.service.input.MouseEvent;
import andioopp.model.domain.player.TowerCard;

/**
 * Controls dragging behaviour for a {@link TowerCard}.
 */
public class TowerCardDraggableController extends Draggable<TowerCardDragEvent> {
    private final TowerCard<?> card;

    public TowerCardDraggableController(RectanglePlupp rectangle, TowerCard<?> card) {
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
