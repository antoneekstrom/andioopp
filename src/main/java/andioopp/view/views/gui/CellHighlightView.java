package andioopp.view.views.gui;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.controller.controllers.CellDroppableController;
import andioopp.controller.controllers.TowerCardDragEvent;
import andioopp.controller.input.DragAndDrop;
import andioopp.model.Model;
import andioopp.model.domain.money.Money;
import andioopp.view.View;

import java.util.Collection;

public class CellHighlightView implements View<Model> {

    public static final Color AVAILABLE = new Color(255, 255, 255, 0.7f);
    public static final Color UNAVAILABLE = new Color(255, 0, 0);
    private final DragAndDrop<TowerCardDragEvent> dragAndDrop;
    private final Collection<CellDroppableController> cellDroppableControllers;

    public CellHighlightView(DragAndDrop<TowerCardDragEvent> dragAndDrop, Collection<CellDroppableController> cellDroppableControllers) {
        this.dragAndDrop = dragAndDrop;
        this.cellDroppableControllers = cellDroppableControllers;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        if (dragAndDrop.isDragging()) {
            for (CellDroppableController cellDroppableController : cellDroppableControllers) {
                Rectangle cellRect = cellDroppableController.getArea();
                if (cellRect.contains(dragAndDrop.getMousePosition())) {
                    Color color = canPlaceTower(model, cellDroppableController) ? AVAILABLE : UNAVAILABLE;
                    renderer.drawRectangle(cellRect, color);
                }
            }
        }
    }

    private boolean canPlaceTower(Model model, CellDroppableController cellDroppableController) {
        Money cost = dragAndDrop.getDragData().getCard().getCost();
        boolean canSpend = model.getPlayer().getMoney().canSpend(cost);
        boolean hasTower = cellDroppableController.getCell().hasTower();
        return !hasTower && canSpend;
    }
}
