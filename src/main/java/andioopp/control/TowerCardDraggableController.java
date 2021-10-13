package andioopp.control;

import andioopp.common.transform.Rectangle;
import andioopp.model.player.TowerCard;
import andioopp.service.infrastructure.input.Draggable;
import andioopp.service.infrastructure.input.MouseEvent;

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
