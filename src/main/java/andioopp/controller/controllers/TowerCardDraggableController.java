package andioopp.controller.controllers;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.controller.input.Draggable;
import andioopp.controller.input.MouseInputEvent;
import andioopp.model.Model;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;

/**
 * Controls dragging behaviour for a {@link TowerCard}.
 */
public class TowerCardDraggableController extends Draggable<TowerCardDragEvent> {
    private final TowerCard<? extends Tower> card;
    private final Model model;

    public TowerCardDraggableController(Rectangle rectangle, TowerCard<?> card, Model model) {
        super(rectangle);
        this.card = card;
        this.model = model;
    }

    @Override
    protected TowerCardDragEvent getDragData() {
        if (model.getPlayer().getMoney().canSpend(card.getCost())) {
            return new TowerCardDragEvent(card);
        }
        return null;
    }

    @Override
    public void onEvent(MouseInputEvent event) {
    }
}
