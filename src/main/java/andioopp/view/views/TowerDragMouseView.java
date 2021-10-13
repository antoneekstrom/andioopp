package andioopp.view.views;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.control.TowerCardDragEvent;
import andioopp.model.Model;
import andioopp.service.infrastructure.input.DragAndDropService;
import andioopp.view.View;

public class TowerDragMouseView <S extends Sprite<?>> implements View<S> {

    private final DragAndDropService<TowerCardDragEvent> dragAndDropService;
    private final EnemiesView<S> enemiesView;
    private final TransformFactory transformFactory;

    public TowerDragMouseView(DragAndDropService<TowerCardDragEvent> dragAndDropService, EnemiesView<S> enemiesView, TransformFactory transformFactory) {
        this.dragAndDropService = dragAndDropService;
        this.enemiesView = enemiesView;
        this.transformFactory = transformFactory;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        if (dragAndDropService.isDragging()) {
            S sprite = dragAndDropService.getDragData().getCard().getSupplier().get().getSprite(renderer.getSpriteFactory());
            Dimension size = enemiesView.getEntitySize(model.getWorld(), sprite);
            Transform transform = transformFactory.createWithPosition(dragAndDropService.getMousePosition().sub(size.toVector().scale(0.5f)));

            renderer.drawSprite(sprite, transform, size);
        }
    }
}
