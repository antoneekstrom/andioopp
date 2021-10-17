package andioopp.controller.controllers;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.controller.input.Droppable;
import andioopp.model.Model;
import andioopp.model.domain.money.Transaction;
import andioopp.model.domain.player.TowerCard;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.TowerSupplier;
import andioopp.model.domain.world.Cell;
import andioopp.model.util.ModelCoordinate;

/**
 * Controls dropping behaviour for a {@link Cell}.
 */
public class CellDroppableController extends Droppable<TowerCardDragEvent> {

    private final Model model;
    private final int row;
    private final int col;

    public CellDroppableController(Rectangle rectangle, Model model, int row, int col) {
        super(rectangle);
        this.model = model;
        this.row = row;
        this.col = col;
    }

    private ModelCoordinate getTowerPosition() {
        return new ModelCoordinate(row, col);
    }

    private Cell getCell() {
        return model.getWorld().getCell(row, col);
    }

    @Override
    public void onEvent(TowerCardDragEvent event) {
        Cell cell = getCell();

        if (!cell.hasTower()) {
            TowerCard<? extends Tower> card = event.getCard();
            Transaction<? extends TowerSupplier<? extends Tower>> transaction = model.getPlayer().buy(card);
            if (transaction.isSuccessful()) {
                Tower result = transaction.getResult().create(getTowerPosition());
                cell.setTower(result);
            }
        }
    }
}
