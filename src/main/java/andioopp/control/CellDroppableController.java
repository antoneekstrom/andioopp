package andioopp.control;

import andioopp.common.transform.Rectangle;
import andioopp.model.Model;
import andioopp.service.infrastructure.input.Droppable;

public class CellDroppableController extends Droppable<TowerDragEvent> {

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
    public void onEvent(TowerDragEvent event) {
        System.out.println(event);
        // model.getWorld().getCell(row, col).setTower(event.getTower());
    }
}
