package andioopp.control;

import andioopp.model.player.TransactionSupplier;
import andioopp.model.tower.Tower;

public class TowerCardDragEvent {

    private final TransactionSupplier<? extends Tower> card;

    public TowerCardDragEvent(TransactionSupplier<? extends Tower> card) {
        this.card = card;
    }

    public TransactionSupplier<? extends Tower> getCard() {
        return card;
    }
}
