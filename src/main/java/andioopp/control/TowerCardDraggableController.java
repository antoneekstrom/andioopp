package andioopp.control;

import andioopp.service.infrastructure.input.MouseEvent;
import andioopp.common.transform.Rectangle;
import andioopp.model.tower.Tower;
import andioopp.service.infrastructure.input.Draggable;

import java.util.function.Supplier;

public class TowerCardDraggableController extends Draggable<TowerDragEvent> {

    private final Supplier<Tower> towerSupplier;

    public TowerCardDraggableController(Rectangle rectangle, Supplier<Tower> towerSupplier) {
        super(rectangle);
        this.towerSupplier = towerSupplier;
    }

    @Override
    protected TowerDragEvent getDragData() {
        return new TowerDragEvent(towerSupplier.get());
    }

    @Override
    public void onEvent(MouseEvent event) {

    }
}
