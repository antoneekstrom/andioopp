package andioopp.view.views.gui;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.controller.controllers.TowerCardDragEvent;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;
import andioopp.view.views.world.EnemiesView;
import andioopp.controller.service.input.DragAndDropService;
import andioopp.view.View;

public class TowerDragMouseView implements View<Model> {

    private final DragAndDropService<TowerCardDragEvent> dragAndDropService;
    private final EnemiesView enemiesView;


    public TowerDragMouseView(DragAndDropService<TowerCardDragEvent> dragAndDropService, EnemiesView enemiesView) {
        this.dragAndDropService = dragAndDropService;
        this.enemiesView = enemiesView;

    }

  /*  @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        if (dragAndDropService.isDragging()) {
            S sprite = dragAndDropService.getDragData().getCard().getSupplier().get().getSprite(renderer.getSpriteFactory());
            Dimension size = enemiesView.getEntitySize(model.getWorld(), sprite);
            Transform transform = transformFactory.createWithPosition(dragAndDropService.getMousePosition().sub(size.toVector().scale(0.5f)));

            renderer.drawSprite(sprite, transform, size);
        }
    }*/

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        if (dragAndDropService.isDragging()) {
            Tower tower = dragAndDropService.getDragData().getCard().getSupplier().get();
            S sprite = dragAndDropService.getDragData().getCard().getSupplier().get().getSprite(renderer.getSpriteFactory());
            Dimension<ViewCoordinate> viewSize = viewport.getViewSize(tower.getSize());
            //Dimension<ViewCoordinate> viewSize = enemiesView.getEntitySize(model.getWorld(), sprite);viewport.getViewSize(getSize());
            ViewCoordinate viewPosition = viewport.getViewCoordinate(new ModelCoordinate(dragAndDropService.getMousePosition().sub(viewSize.toVector().scale(0.5f))));

            renderer.drawSprite(sprite, viewPosition, viewSize);
        }
    }
}
