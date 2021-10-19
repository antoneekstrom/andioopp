package andioopp.view.views.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.controller.controllers.TowerCardDragEvent;
import andioopp.model.Model;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;
import andioopp.controller.input.DragAndDrop;
import andioopp.view.View;
import andioopp.view.views.world.TowersView;

public class TowerDragMouseView implements View<Model> {

    private final DragAndDrop<TowerCardDragEvent> dragAndDrop;
    private final TowersView towersView;

    public TowerDragMouseView(DragAndDrop<TowerCardDragEvent> dragAndDrop, TowersView towersView) {
        this.dragAndDrop = dragAndDrop;
        this.towersView = towersView;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        if (dragAndDrop.isDragging()) {
            Tower tower = getTower();
            S sprite = renderer.getSpriteFactory().get(tower.getSprite());

            Dimension size = towersView.getTowerSizeFromSprite(sprite);
            Vector3f mouse = dragAndDrop.getMousePosition();
            Vector3f position = mouse.sub(size.halved().toVector());

            renderer.drawSprite(sprite, position, size);
        }
    }

    private Tower getTower() {
        return dragAndDrop.getDragData().getCard().getSupplier().create(new ModelCoordinate(Vector3f.ZERO));
    }
}
