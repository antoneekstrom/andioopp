package andioopp.control;

import andioopp.model.player.TowerCard;
import andioopp.model.player.TransactionSupplier;
import andioopp.model.tower.Tower;

public class TowerCardDragEvent {

    private final TowerCard<? extends Tower> card;

    public TowerCardDragEvent(TowerCard<? extends Tower> card) {
        this.card = card;
    }

    public TowerCard<? extends Tower> getCard() {
        return card;
    }
}
