package andioopp.control;

import andioopp.common.transform.Rectangle;
import andioopp.model.Model;
import andioopp.model.player.Transaction;
import andioopp.model.tower.Tower;
import andioopp.model.world.Cell;
import andioopp.service.infrastructure.input.Droppable;

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
