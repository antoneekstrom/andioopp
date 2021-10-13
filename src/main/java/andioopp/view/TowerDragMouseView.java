package andioopp.view;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.transform.*;
import andioopp.control.TowerCardDragEvent;
import andioopp.model.Model;
import andioopp.service.infrastructure.input.DragAndDropService;

public class TowerDragMouseView <S extends Sprite<?>> extends DependentView<S, DragAndDropService<TowerCardDragEvent>> {

    public TowerDragMouseView(DragAndDropService<TowerCardDragEvent> dragAndDropService) {
        super(dragAndDropService);
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        DragAndDropService<TowerCardDragEvent> dragAndDropService = getDependency();
        TransformFactory transformFactory = ConcreteTransform.getFactory();


        if (dragAndDropService.isDragging()) {
            S sprite = dragAndDropService.getDragData().getCard().getSupplier().get().getSprite(renderer.getSpriteFactory());
            Dimension size = new EnemiesView<S>(new Rectangle(0, 0, 1280, 720*0.7f), transformFactory).getEntitySize(model.getWorld(), sprite);
            Transform transform = transformFactory.createWithPosition(dragAndDropService.getMousePosition().sub(size.toVector().scale(0.5f)));

            renderer.drawSprite(sprite, transform, size);
        }
    }
}
