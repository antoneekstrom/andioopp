package andioopp.controller.controllers;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.controller.input.Draggable;
import andioopp.controller.input.MouseInputEvent;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;

/**
 * Controls dragging behaviour for a {@link TowerCard}.
 */
public class TowerCardDraggableController extends Draggable<TowerCardDragEvent> {
    private final TowerCard<? extends Tower> card;

    public TowerCardDraggableController(Rectangle rectangle, TowerCard<?> card) {
        super(rectangle);
        this.card = card;
    }

    @Override
    protected TowerCardDragEvent getDragData() {
        return new TowerCardDragEvent(card);
    }

    @Override
    public void onEvent(MouseInputEvent event) {
    }
}
