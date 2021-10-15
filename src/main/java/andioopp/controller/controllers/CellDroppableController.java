package andioopp.controller.controllers;

import andioopp.common.math.rectangle.Rectangle;
import andioopp.controller.service.input.Droppable;
import andioopp.model.Model;
import andioopp.model.domain.money.Transaction;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.Cell;

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

    @Override
    public void onEvent(TowerCardDragEvent event) {
        Cell cell = getCell();
        if (!cell.hasTower()) {
            Transaction<? extends Tower> transaction = model.getPlayer().buy(event.getCard());
            if (transaction.isSuccessful()) {
                try {
                    cell.setTower(transaction.getResult());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Cell getCell() {
        return model.getWorld().getCell(row, col);
    }
}
