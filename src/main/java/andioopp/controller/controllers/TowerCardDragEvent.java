package andioopp.controller.controllers;

import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;

public class TowerCardDragEvent {

    private final TowerCard<? extends Tower> card;

    public TowerCardDragEvent(TowerCard<? extends Tower> card) {
        this.card = card;
    }

    public TowerCard<? extends Tower> getCard() {
        return card;
    }
}
